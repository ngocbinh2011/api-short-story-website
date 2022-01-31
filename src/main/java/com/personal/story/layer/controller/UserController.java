package com.personal.story.layer.controller;

import com.personal.story.layer.application.entity.User;
import com.personal.story.layer.application.exception.InvalidDataExeception;
import com.personal.story.layer.application.service.IUserService;
import com.personal.story.layer.application.service.impl.UserService;
import com.personal.story.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNewUser(@RequestBody User user) {
        try {
            if (user == null) {
                throw new InvalidDataExeception("User must not be null!");
            }
            User userCheckExists = userService.getByUsername(user.getUsername());
            if (userCheckExists == null) {
                user.setCreatedAt(System.currentTimeMillis());
                user.setUpdatedAt(System.currentTimeMillis());
                User result = userService.save(user);
                logger.info(String.format("Create new user[id=%d] completed!", result.getId()));
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else {
                return Response.format(null, HttpStatus.BAD_REQUEST, "Username already exists!");
            }
        } catch (InvalidDataExeception e) {
            logger.error("Save user failed!", e);
            return Response.format(null, HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Throwable e) {
            String message = "Internal Server Error";
            logger.error(message);
            return Response.format(null, HttpStatus.INTERNAL_SERVER_ERROR, message);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }

}
