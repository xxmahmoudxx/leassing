package com.leasing.leasing.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="Lah8PQFFrfbsCQcgIqiGVKfdqY9C0JjMvK9ffu746IoWYeD677aWVjoa2yJ+YMNbrmcyV7K+CD9JIfRN/pzfQgYUf81WhjWDt+8IO3WBnyfCPuGvF9XS92YuE33NYwmzO+ogPc8cnqAmZn/Noy4Mcu+c63DsZKuc9cqYsjs9+WKPCijffRWCwMr1oVScSELctrDLwYU5JjjKBWSMSVrGaLP2w21RsBvYxgrNcCzY8NCyE5CyWlv8mZ7APeB+LTJYzqYnD0c7CoU34DYzdsg1c+qzZwRCE8Ehu0A9KJYpkUFbiavf35b4S3BUY+9jyStvVo9CjoKNs5XFzSJQDBuZQ7yBPB+e7v4gnAePzZwaki0=";
    public String extractUsername(String token) {
        return extractClaim(token,Claims :: getSubject);

    }

    public <T> T extractClaim (String token , Function <Claims,T> claimsResolver ){
        final Claims claims= ExtaractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateTokens(new HashMap<>(),userDetails);
    }
    public String generateTokens(Map<String,Object>extraClaims, UserDetails userDetails){
       return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();

    }
    public boolean  isTokenValid(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    private Claims ExtaractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJwt(token).getBody();

    }

    private Key getSignInKey() {
        byte [] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
