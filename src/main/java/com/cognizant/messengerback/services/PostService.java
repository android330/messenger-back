package com.cognizant.messengerback.services;

import com.cognizant.messengerback.models.DTO.PostDTO;
import com.cognizant.messengerback.models.Post;
import com.cognizant.messengerback.models.User;
import com.cognizant.messengerback.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private UserService userService;
    private PostRepository postRepository;

    private SseEmitters emitters = new SseEmitters();

    @Autowired
    PostService(UserService userService, PostRepository postRepository){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostDTO> getAllPosts(){
        List<Post> postsBackend = postRepository.findAll();

        List<PostDTO> returnList = new ArrayList<>();
        for(Post post : postsBackend){
            User user = userService.findById(post.getUserId());

            returnList.add(new PostDTO(post.getId(),user,post.getMessage(),post.getPicture(), post.getDate()));
            //TODO: Implement hasmap to keep track of already retrieved user ids
        }
        return returnList;
    }

    public String createPost(Post post){
        try {
            postRepository.save(post);

            User user = userService.findById(post.getUserId());
            emitters.send(new PostDTO(post.getId(),user,post.getMessage(),post.getPicture(), post.getDate()));


            return "True";
        }catch (Exception e){
            return null;
        }
    }

    public SseEmitter getPostStream(){
        SseEmitter emitter = new SseEmitter(-1L);
        emitters.add(emitter);

        return emitter;
    }

}
