package com.leasing.leasing.leasingController;

import com.leasing.leasing.leasingModel.clientPhysique;
import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingService.ClientPhysiqueService;
import com.leasing.leasing.leasingService.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/ClientPhysique")
@CrossOrigin(origins="*")
public class ClientPhysiqueController {
    ClientPhysiqueService cps;
    private static final Logger LOG = LoggerFactory.getLogger(ClientPhysiqueController.class);

    @Autowired
    public ClientPhysiqueController(ClientPhysiqueService cps) {
        this.cps = cps;
    }


    @PostMapping("/inscreptionClientphysique")
    public void insert(@RequestBody clientPhysique clrph) {
        LOG.info("Insert client physique data");
        cps.save(clrph);

    }

    @PostMapping("/identhifierClientphysique")
    public String vrif(@RequestBody Map<String, String> request) {
        String mail = request.get("mail");
        String pwd = request.get("password");
        return cps.verif(mail, pwd);
    }

    @GetMapping("/getClientphysique/{id}")
    public user getbyid(@PathVariable("id") String id) {
        return cps.getcltpByid(id);

    }

    @PutMapping("/update")
    public void modify(@RequestBody clientPhysique a){
        cps.modifier(a);


    }
    @DeleteMapping("/deleteClientPhysique/{id}")
    public void delte(@PathVariable ("id") String id){
        cps.delete(id);
    }


}
