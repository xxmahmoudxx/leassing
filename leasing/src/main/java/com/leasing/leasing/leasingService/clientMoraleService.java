package com.leasing.leasing.leasingService;

import com.leasing.leasing.leasingModel.client;
import com.leasing.leasing.leasingModel.clientMoral;
import com.leasing.leasing.leasingModel.clientPhysique;
import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingRepository.clientMoraleRepository;
import com.leasing.leasing.leasingRepository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;

@Service
public class clientMoraleService implements CommandLineRunner {

    private clientMoraleRepository cmr;
    private final String salt ;

    @Override
    public void run(String... args) throws Exception {
    }
    @Value("${encryption.secretKey}")
    private String secretKey;



    @Autowired

    public clientMoraleService(clientMoraleRepository cmr) {
        this.cmr=cmr;
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


    public void save(clientMoral clt) {
        clt.setPassword(encrypt(clt.getPassword()));
        clt.setMail(encrypt(clt.getMail()));
        clt.setIdentifiant_unique(encrypt(clt.getIdentifiant_unique()));
        cmr.save(clt);



    }
    public String verif(String mail,String pwd){
        String mail1=encrypt(mail);

        Optional<client> clt;
        clt=cmr.findBymail(mail1);

        if (clt.isPresent()){
            if(Objects.equals(decrypt(clt.get().getPassword()), pwd)){
                return "correcte";
            }
            else
                return   "incorrecte";}
        else
            return mail+" nexiste pas";

    }
    public  user getcltmoralByid(String id)
    {
        return cmr.findById(id).get();
    }


    public void modifier(clientMoral cltp){
        cltp.setPassword(encrypt(cltp.getPassword()));
        cltp.setMail(encrypt((cltp.getPassword())));
        cmr.save(cltp);
    }


    public void delete(String id) {
        cmr.deleteById(id);
    }



}
