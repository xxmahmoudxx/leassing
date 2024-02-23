package com.leasing.leasing.Auth;

import com.leasing.leasing.leasingModel.user;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Authorisation")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class AuthticationController {
    private final AuthenticationService Service;
    @PostMapping("/clientPhysique")
    public ResponseEntity<AuthenticationResponse>Register(@RequestBody RegisterRequestClientPhysique request ){
      return  ResponseEntity.ok(Service.registerClientPhysiqye(request));

    }
    @PostMapping("/clientMorale")
    public ResponseEntity<AuthenticationResponse>Register(@RequestBody RegisterRequestClientMorale request ){
        return  ResponseEntity.ok(Service.registerClientMoral(request));

    }
    @PostMapping("/Admin")
    public ResponseEntity<AuthenticationResponse>Register(@RequestBody RegisterRequestAdmin request){
        return ResponseEntity.ok(Service.registreAdmin(request));
    }
    @PostMapping("/authentication")
    public  ResponseEntity<AuthenticationResponse> authenticateAdmin(@RequestBody AuthenticationRequest request){
  return ResponseEntity.ok(Service.authenticate(request));
    }

}
