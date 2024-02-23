package com.leasing.leasing.leasingRepository;

import com.leasing.leasing.leasingModel.clientPhysique;
import com.leasing.leasing.leasingModel.voiture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface clientPhysiqueRepository extends MongoRepository<clientPhysique,String> {
    Optional<clientPhysique> findBymail(String mail1);
    boolean existsBymail(String email);

}
