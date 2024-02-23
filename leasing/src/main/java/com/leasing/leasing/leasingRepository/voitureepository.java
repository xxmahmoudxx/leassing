package com.leasing.leasing.leasingRepository;

import com.leasing.leasing.leasingModel.voiture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface voitureepository extends MongoRepository<voiture,String> {
    List<voiture > findAllByPrixLessThanEqualOrderByPrixAsc(double rix);
    List <voiture> findAllByKelometrageLessThanEqualOrderByKelometrageAsc(int km);

    List <voiture> findAllByestelectrique(boolean a);

    List <voiture> findAllByestautomatique(boolean a);

    List <voiture> findAllBymarque(String m);

    List <voiture> findAllBymodel(String m);

}
