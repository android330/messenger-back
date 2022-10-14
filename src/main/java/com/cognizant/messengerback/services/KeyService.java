package com.cognizant.messengerback.services;

import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.security.Key;

@Service
public class KeyService {
    private Key key;

    public KeyService(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256); // for example
            this.key =  keyGen.generateKey();
        }catch (Exception ignored){}

    }

    public Key getSecretKey(){
        //System.out.println(key);
        return key;
    }
}
