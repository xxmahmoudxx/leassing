package com.leasing.leasing.leasingController;

import com.leasing.leasing.leasingModel.clientMoral;
import com.leasing.leasing.leasingModel.clientPhysique;
import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingService.ClientPhysiqueService;
import com.leasing.leasing.leasingService.clientMoraleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/ClientMorale")
@CrossOrigin(origins="*")
public class ClientMoraleController {

    clientMoraleService cms;
    private static final Logger LOG = LoggerFactory.getLogger(ClientMoraleController.class);


    @Autowired
    public ClientMoraleController(clientMoraleService cms) {
        this.cms = cms;
    }


    @PostMapping("/inscreptionClientMorale")
    public void insert(@RequestBody clientMoral clrph) {
        LOG.info("Insert client Moral data");
        cms.save(clrph);

    }
    @PostMapping("/identhifierClientMorale")
    public String vrif(@RequestBody Map<String, String> request) {
        String mail = request.get("mail");
        String pwd = request.get("password");
        return  cms.verif(mail, pwd);
    }
    @GetMapping("/getClientMorale/{id}")
    public user getbyid(@PathVariable("id") String id) {
        return cms.getcltmoralByid(id);

    }

    @PutMapping("/updateClientMorale")
    public void modify(@RequestBody clientMoral a){
        cms.modifier(a);


    }

    @DeleteMapping("/deleteClientMorale/{id}")
    public void delte(@PathVariable ("id") String id){
        cms.delete(id);
    }









}
