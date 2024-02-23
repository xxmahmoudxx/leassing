package com.leasing.leasing.leasingService;

import com.leasing.leasing.leasingModel.clientPhysique;
import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingRepository.clientMoraleRepository;
import com.leasing.leasing.leasingRepository.clientPhysiqueRepository;
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
public class ClientPhysiqueService implements CommandLineRunner {
    private clientPhysiqueRepository cpr;
    private final String salt ;

   
    @Override
    public void run(String... args) throws Exception {
    }
    @Value("${encryption.secretKey}")
    private String secretKey;


    @Autowired

    public ClientPhysiqueService(clientPhysiqueRepository cpr) {
        this.cpr=cpr;
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
    public void save(clientPhysique cltp) {
        cltp.setPassword(encrypt(cltp.getPassword()));
        cltp.setMail(encrypt(cltp.getMail()));
        cltp.setNumidentite(encrypt(cltp.getNumidentite()));
        cpr.save(cltp);
    }
    public String verif(String mail,String pwd){
        String mail1=encrypt(mail);

        Optional<clientPhysique>cltp;
        cltp=cpr.findBymail(mail1);

        if (cltp.isPresent()){
            if(Objects.equals(decrypt(cltp.get().getPassword()), pwd)){
                return "correcte";
            }
            else
                return   "incorrecte";}
        else
            return mail+" nexiste pas";

    }
    public  user getcltpByid(String id)
    {
        return cpr.findById(id).get();
    }
    public void modifier(clientPhysique cltp){
        cltp.setPassword(encrypt(cltp.getPassword()));
        cltp.setMail(encrypt((cltp.getPassword())));
        cpr.save(cltp);
    }

    public void delete(String id){
        cpr.deleteById(id);
    }



}
