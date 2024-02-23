package com.leasing.leasing.leasingRepository;

import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingService.userService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.util.Optional;

public interface userRepository extends MongoRepository<user,String> {

    Optional<user> findBymail(String mail);
    boolean existsBymail(String email);



}
