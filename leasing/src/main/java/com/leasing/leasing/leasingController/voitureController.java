package com.leasing.leasing.leasingController;

import com.leasing.leasing.leasingModel.voiture;
import com.leasing.leasing.leasingService.voitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/voiture")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class voitureController {
 voitureService vs;
    @GetMapping(value = "/allvoitures")
    public List<voiture> getAll() {

        return vs.getAllvoiture()   ;
    }
    @PostMapping("/add")
    public void add(@RequestBody voiture voiture){

        vs.saveOrUpdate(voiture);

    }
    @GetMapping("voiture/{id}")
    public voiture voiture(@PathVariable("id") String id){
        return vs.getvoitureById(id);
    }



    @DeleteMapping("delete/{id}")
    public void deletevoiturebyid(@PathVariable("id")String id){
        vs.delete(id);
    }
    @PutMapping("update")
    public void updatevpoiturebyid(@RequestBody voiture a){
        vs.saveOrUpdate(a);

    }
    @GetMapping("/byprice")
    public List<voiture> grtbyprice(@RequestBody double price){
        return  vs.getvoitureByprice(price);
    }
    @GetMapping("/byelectrique")
    public  List <voiture> getbyprice(@RequestBody boolean a){
        return vs.getvoitureByelectrique(a);
    }
    @GetMapping("/bykilometrage")
    public  List <voiture> getbykilometrage(@RequestBody int k){
        return  vs.getvoitureByKilometrage(k);
    }


    @GetMapping("/byauto")
    public  List <voiture> getbyauto(@RequestBody boolean k){
        return vs.getvoitureByauto(k);
    }

    @GetMapping("/bymarque")
    public  List <voiture> getbymarque(@RequestBody String k){
        return vs.getvoiturebymarque(k);
    }
    @GetMapping("/bymodel")
    public  List <voiture> getbymodel(@RequestBody String k){
        return vs.getvoiturebymodel(k);
    }






    }
