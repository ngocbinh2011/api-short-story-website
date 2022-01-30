package com.personal.story.layer.application.service;

import com.personal.story.layer.application.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IUserService {
    Set<User> getAll();

    User getById(int id);

    User save(User user) throws Exception;

    User getByUsername(String username) throws Exception;
}
