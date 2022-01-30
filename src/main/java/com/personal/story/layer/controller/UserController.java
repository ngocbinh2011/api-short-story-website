package com.personal.story.layer.controller;

import com.personal.story.layer.application.entity.User;
import com.personal.story.layer.application.exception.InvalidDataExeception;
import com.personal.story.layer.application.service.impl.UserService;
import com.personal.story.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            if (user == null) {
                throw new InvalidDataExeception("User must not null!");
            }
            User checkExists = userService.getByUsername(user.getUsername());
            if(checkExists == null){
                user.setCreatedAt(System.currentTimeMillis());
                user.setUpdatedAt(System.currentTimeMillis());
                User result = userService.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else{
                return Response.format(null, HttpStatus.BAD_REQUEST, "username already exists!");
            }
        } catch (Exception e) {
            logger.error("save user failed!", e);
            String message = "save user failed!";
            return Response.format(null, HttpStatus.BAD_REQUEST, message);
        }
    }

}
