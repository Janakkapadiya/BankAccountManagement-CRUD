package com.accountmanagement.practice.Controller;

import com.accountmanagement.practice.ConfigurationAndSecurity.JwtHelper;
import com.accountmanagement.practice.Services.CostumUserdetailService;
import com.accountmanagement.practice.dto.TokenReq.Tokenreq;
import com.accountmanagement.practice.dto.TokenRes.TokenRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

     @Autowired
     private JwtHelper jwtHelper;
     @Autowired
     private CostumUserdetailService costumUserdetailService;
     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private PasswordEncoder passwordEncoder;

     @PostMapping("/authenticate")
     public ResponseEntity<?> generateToken(@RequestBody Tokenreq dto) throws Exception {
         try{
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
         }catch(UsernameNotFoundException e){
             e.printStackTrace();
             throw  new Exception("Bed credentials");
     }
         UserDetails userDetails = this.costumUserdetailService.loadUserByUsername(dto.getUsername());

         String token = this.jwtHelper.generateToken(userDetails);
         return ResponseEntity.ok(new TokenRes(token));
     }
}



