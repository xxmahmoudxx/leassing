package com.leasing.leasing.leasingController;

import com.leasing.leasing.leasingModel.user;
import com.leasing.leasing.leasingService.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins="*")
public class userControler {

    userService us;
    private static final Logger LOG = LoggerFactory.getLogger(userControler.class);
    @Autowired
    public userControler(userService us) {
        this.us = us;
    }


    @PostMapping("/inscreption")
    public void insert(@RequestBody user user){
        LOG.info("Insert user data");
        us.save(user);

    }
    @PostMapping("/identhifier")
    public String vrif(@RequestBody Map<String, String> request){
        String mail = request.get("mail");
        String pwd = request.get("password");
        return us.verif(mail,pwd);
    }
    @GetMapping("/get/{id}")
    public user getbyid(@PathVariable("id")String id){
  return  us.getUserByid(id);
    }
    @PutMapping("/update")
        public void modify(@RequestBody user a){
            us.modifier(a);


        }
        @DeleteMapping("/delete/{id}")
    public void delte(@PathVariable ("id") String id){
        us.delete(id);
        }
    }


