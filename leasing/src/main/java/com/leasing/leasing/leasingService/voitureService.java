package com.leasing.leasing.leasingService;

import com.leasing.leasing.leasingModel.voiture;
import com.leasing.leasing.leasingRepository.voitureepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class voitureService implements CommandLineRunner {
    private voitureepository vr;



    @Override
    public void run(String... args) throws Exception {
    }

    public List<voiture> getAllvoiture()
    {
        List<voiture> voiture = new ArrayList<voiture>();
        vr.findAll().forEach(a -> voiture.add(a));
        return voiture;
    }


    public voiture getvoitureById(String id)
    {
        return vr.findById(id).get();
    }
    public void saveOrUpdate(voiture voiture) {
        vr.save(voiture);
    }
    public void delete(String id)
    {
        vr.deleteById(id);
    }
    public List <voiture> getvoitureByprice(double price){
        return vr.findAllByPrixLessThanEqualOrderByPrixAsc(price);

    }

    public List <voiture> getvoitureByKilometrage(int km){
        return vr.findAllByKelometrageLessThanEqualOrderByKelometrageAsc(km);
    }
    public List <voiture> getvoitureByelectrique(boolean a){
        return vr.findAllByestelectrique(a);
    }

public List<voiture> getvoitureByauto (boolean a) {
       return vr.findAllByestautomatique(a);

}
public List<voiture>getvoiturebymarque(String a){
        return vr.findAllBymarque(a);
}
 public List <voiture> getvoiturebymodel(String a){
        return vr.findAllBymodel(a);
 }



}
