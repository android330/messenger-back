package com.cognizant.messengerback.services;

import com.cognizant.messengerback.models.AuthUser;
import com.cognizant.messengerback.models.DTO.UserDTO;
import com.cognizant.messengerback.repositories.AuthRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private  final AuthRepository authRepository;
    PasswordEncoder passwordEncoder;
    JWTService jwtService;

    @Autowired
    public AuthService(AuthRepository authRepository, JWTService jwtService){
        this.authRepository = authRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String checkLogin(UserDTO user) {
        String givenUsername = user.getUsername();
        String givenPassword = user.getPassword();
        AuthUser user1 =  authRepository.findByUsername(givenUsername);

        //System.out.println(passwordEncoder.encode(givenPassword));

        if(user1 != null){
            if(passwordEncoder.matches(givenPassword,user1.getPassword())){
                return jwtService.generateToken(user1);
            }else {
                return null;
            }
        }else {
            return null;
        }

    }

    public String verifyCredentials(String token) {
        AuthUser user = jwtService.extractUserFromTokenAndVerify(token);
        if(user == null)
            return null;

        if(authRepository.findByUsername(user.getUsername()) == null)
            return null;
        return "true";

    }

}
