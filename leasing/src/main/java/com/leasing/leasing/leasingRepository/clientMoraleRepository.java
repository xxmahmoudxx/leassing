package com.leasing.leasing.leasingRepository;

import com.leasing.leasing.leasingModel.client;
import com.leasing.leasing.leasingModel.clientMoral;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface clientMoraleRepository extends MongoRepository<clientMoral,String> {
    Optional<client> findBymail(String mail);
    boolean existsBymail(String email);

}
