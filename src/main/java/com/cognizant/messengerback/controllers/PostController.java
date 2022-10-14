package com.cognizant.messengerback.controllers;

import com.cognizant.messengerback.models.AuthUser;
import com.cognizant.messengerback.models.DTO.PostDTO;
import com.cognizant.messengerback.models.Post;
import com.cognizant.messengerback.services.AuthService;
import com.cognizant.messengerback.services.JWTService;
import com.cognizant.messengerback.services.PostService;
import com.cognizant.messengerback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("http://localhost:3000")
public class PostController {
    private PostService postService;
    private AuthService authService;
    private UserService userService;
    private JWTService jwtService;

    @Autowired
    PostController(PostService postService, AuthService authService, UserService userService, JWTService jwtService){
        this.postService = postService;
        this.authService = authService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody HashMap<String, String> request){
        try {
            String token = request.get("token").split(" ")[1];
            String message = request.get("message");
//            if(message.equals("asdf123")){
//                int i = 0;
//            }
            AuthUser user = jwtService.extractUserFromTokenAndVerify(token);
            String r = postService.createPost(
                new Post(userService.findByUsername(user.getUsername()).getId(), message, null, new Date()));
            return ResponseEntity.ok(r);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Credential Validation Error");
        }
    }

    @GetMapping("/update")
    public SseEmitter streamPosts(){
        return postService.getPostStream();
    }
}
