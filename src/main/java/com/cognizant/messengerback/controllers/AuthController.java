package com.cognizant.messengerback.controllers;

import com.cognizant.messengerback.models.AuthUser;
import com.cognizant.messengerback.models.DTO.UserDTO;
import com.cognizant.messengerback.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody HashMap<String, String> credentials) {
        // System.out.println(credentials);
        String token = authService.checkLogin(
                UserDTO.builder()
                        .username(credentials.get("username"))
                        .password(credentials.get("password"))
                        .build());
//        System.err.println(token);
        if(token != null){
            return ResponseEntity.ok("Bearer " + token);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<String> verifyTokenCredentials(@RequestBody String token){
//        System.err.println(token);
        String response = null;
        try{
//            System.err.println(Arrays.toString(token.split("[+]")));
            String splitToken = token.split("[+]")[1];
//            System.err.println(splitToken);

            String sendToken = splitToken.substring(0, splitToken.length()-1);

//            System.err.println(sendToken);
            response = authService.verifyCredentials(sendToken);
        }catch (ArrayIndexOutOfBoundsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }


        if(response != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged In");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }
}
