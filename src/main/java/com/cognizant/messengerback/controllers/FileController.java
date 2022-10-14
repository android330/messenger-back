package com.cognizant.messengerback.controllers;

import com.cognizant.messengerback.models.AuthUser;
import com.cognizant.messengerback.models.FileInfo;
import com.cognizant.messengerback.models.Post;
import com.cognizant.messengerback.services.FileStorateService;
import com.cognizant.messengerback.services.JWTService;
import com.cognizant.messengerback.services.PostService;
import com.cognizant.messengerback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
public class FileController {
    @Autowired
    FileStorateService fileStorateService;

    @Autowired
    JWTService jwtService;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("token") String jwt){
        String token = jwt.split(" ")[1];
        AuthUser user = jwtService.extractUserFromTokenAndVerify(token);

        String message = "";
        try{
            if(file.getSize() > 1048576)
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File to large");
            fileStorateService.save(file);

            String r = postService.createPost(new Post(
                    userService.findByUsername(user.getUsername()).getId(),
                    null,
                    ( "http://localhost:8080/files/" + file.getOriginalFilename()),
                    new Date()));

            message = "Uploaded file sucessfully: " + file.getOriginalFilename();
            return ResponseEntity.ok(message);
        }catch (Exception e){
            message = "Could not upload file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles(){
        List<FileInfo> fileInfos = fileStorateService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "getFile", path.getFileName().toString())
                    .build()
                    .toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileStorateService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);

    }

}
