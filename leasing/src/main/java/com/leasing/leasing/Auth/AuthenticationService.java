package com.leasing.leasing.Auth;

import com.leasing.leasing.config.JwtService;
import com.leasing.leasing.leasingModel.Role;
import com.leasing.leasing.leasingModel.clientMoral;
import com.leasing.leasing.leasingModel.clientPhysique;
import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingRepository.clientMoraleRepository;
import com.leasing.leasing.leasingRepository.clientPhysiqueRepository;
import com.leasing.leasing.leasingRepository.userRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final clientPhysiqueRepository ClientPhysiqueRepository;
    private final clientMoraleRepository  clientMoraleRepository ;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse registerClientPhysiqye(RegisterRequestClientPhysique request) {
        var user= clientPhysique.builder()
                .nom(request.getNom())
                .Prenom(request.getPrenom())
                .civilite(request.getCivilite())
                .fonction(request.getFonction())
                .nature_identite(request.getNature_identite())
                .nomComercial(request.getNomComercial())
                .numidentite(request.getNumidentite())
                .date_entree(request.getDate_entree()) // Fixed the method name
                .Rue(request.getRue()) // Assuming these are correct method names
                .code_postal(request.getCode_postal())
                .Localite(request.getLocalite())
                .gouvernorat(request.getGouvernorat())
                .secteur_dactivite(request.getSecteur_dactivité())
                .estClientLeasing(request.isEstClientLeasing())
                .mobile(request.getMobile())
                .telephone(request.getTelephone())
                .mail(request.getMail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ClientPhysique)
                .build();
            save((clientPhysique) user);
        var jwttoken=jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .Token(jwttoken)
                .build();

    }
    public void save (clientPhysique clt){
        ClientPhysiqueRepository.save(clt);
    }


    public AuthenticationResponse registerClientMoral(RegisterRequestClientMorale request) {
        var user = clientMoral.builder()
                .date_entree(request.getDate_entree())
                .Rue(request.getRue())
                .code_postal(request.getCode_postal())
                .Localite(request.getLocalite())
                .gouvernorat(request.getGouvernorat())
                .secteur_dactivite(request.getSecteur_dactivite())
                .estClientLeasing(request.isEstClientLeasing())
                .mobile(request.getMobile())

                .telephone(request.getTelephone())
                .nom_entreprise(request.getNom_entreprise())
                        .raison_sociale(request.getRaison_sociale())

                                .identifiant_unique(request.getIdentifiant_unique())
                        .type(request.getType())
                        .mail(request.getMail())
                        .password(passwordEncoder.encode( request.getPassword()))
                        .role(Role.ClientMorale)
                        .build();


        clientMoraleRepository.save((clientMoral) user);
        var jwttoken=jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .Token(jwttoken)
                .build();

    }
   public AuthenticationResponse  registreAdmin( RegisterRequestAdmin request){
        var admin = user.builder()
                .mail(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(Role.Admin)
                .build();
                 userRepository.save((user) admin);
       var jwttoken=jwtService.generateToken(admin);
       return  AuthenticationResponse.builder()
               .Token(jwttoken)
               .build();


   }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {


        if (userRepository.existsBymail(request.getEmail())){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        var user = userRepository.findBymail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();
    }
    else if (clientMoraleRepository.existsBymail(request.getEmail())) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = clientMoraleRepository.findBymail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }




    else {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = ClientPhysiqueRepository.findBymail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();

        }


        }
        catch (BadCredentialsException e) {
            // Handle incorrect password
            return AuthenticationResponse.builder()
                    .error("Incorrect username or password")
                    .build();
        }

        }






}
