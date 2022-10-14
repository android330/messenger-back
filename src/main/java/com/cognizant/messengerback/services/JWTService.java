package com.cognizant.messengerback.services;

import com.cognizant.messengerback.models.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private static final long MILLISECONDS_IN_A_MINUTE = 60000;
    private static final long EXPIRY_TIME = MILLISECONDS_IN_A_MINUTE * 60;


    @Autowired
    private KeyService keyService;

    JWTService(KeyService keyService){this.keyService = keyService;}

    public String generateToken(AuthUser user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(keyService.getSecretKey(),SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .compact();
    }

    public AuthUser extractUserFromTokenAndVerify(String jwt) {
        try {
            // System.out.println(jwt);
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(keyService.getSecretKey())
                    .build()
                    .parseClaimsJws(jwt);
            if(claims.getBody().getExpiration().before(new Date(System.currentTimeMillis())))
                return null;
            AuthUser claimedUser = new AuthUser();
            claimedUser.setUsername(claims.getBody().get("username", String.class));
            claimedUser.setPassword(claims.getBody().get("password", String.class));
            return claimedUser;
        } catch (Exception e){
            return null;
        }
    }

}
