package com.leasing.leasing.leasingService;

import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingModel.voiture;
import com.leasing.leasing.leasingRepository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;

@Service
public class userService implements CommandLineRunner {
    private userRepository ur;
    private final String salt ;


    @Override
    public void run(String... args) throws Exception {
    }

    @Value("${encryption.secretKey}")
    private String secretKey;

    @Autowired

    public userService(userRepository ur) {
        this.ur=ur;       // Generate a random salt
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[16];
        secureRandom.nextBytes(saltBytes);
        this.salt = bytesToHex(saltBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b & 0xff));
        }
        return hexStringBuilder.toString();
    }
    public String encrypt(String text) {
        TextEncryptor encryptor = Encryptors.text(secretKey, salt);
        return encryptor.encrypt(text);
    }
    public String decrypt(String text) {
        TextEncryptor encryptor = Encryptors.text(secretKey, salt);
        return encryptor.decrypt(text);
    }

    public void save(user userr) {
      userr.setPassword(encrypt(userr.getPassword()));
      userr.setMail(encrypt(userr.getMail()));
        ur.save(userr);
    }
public String verif(String mail,String pwd){
    String mail1=encrypt(mail);

    Optional<user> user;
        user=ur.findBymail(mail1);

       if (user.isPresent()){
           if(Objects.equals(decrypt(user.get().getPassword()), pwd)){
               return "correcte";
           }
            else
               return   "incorrecte";}
       else
           return mail+" nexiste pas";

    }

    public  user getUserByid(String id)
    {
        return ur.findById(id).get();
    }
    public void modifier(user userr){
        userr.setPassword(encrypt(userr.getPassword()));
        userr.setMail(encrypt((userr.getPassword())));
        ur.save(userr);
    }

    public void delete(String id){
        ur.deleteById(id);
    }

}

