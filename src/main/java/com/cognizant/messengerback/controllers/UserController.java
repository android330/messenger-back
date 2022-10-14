package com.cognizant.messengerback.controllers;

import com.cognizant.messengerback.models.User;
import com.cognizant.messengerback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController{
    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id){
        // TODO: MAybe at some point completet when I need mapping for getting user os specific ID
        return ResponseEntity.ok("test");
    }


}
