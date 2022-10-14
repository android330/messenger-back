package com.cognizant.messengerback.services;

import com.cognizant.messengerback.models.User;
import com.cognizant.messengerback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findById(int id){
        User user = userRepository.findById(id);
        if(user != null){
            return user;
        }
        return new User(id,"Unknown", null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
