package com.leasing.leasing.leasingRepository;

import com.leasing.leasing.leasingModel.client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<client,String> {




}
