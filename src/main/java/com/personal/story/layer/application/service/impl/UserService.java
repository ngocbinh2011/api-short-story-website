package com.personal.story.layer.application.service.impl;

import com.personal.story.layer.application.entity.User;
import com.personal.story.layer.application.exception.InvalidDataExeception;
import com.personal.story.layer.application.repository.UserRepository;
import com.personal.story.layer.application.service.IUserService;
import com.personal.story.layer.application.validate.IValidable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IValidable<User> userValidator;

    @Override
    public Set<User> getAll() {
        return new LinkedHashSet<>(userRepository.findAll());
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User save(User user) throws Exception {
        userValidator.validate(user);
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) throws Exception {
        if (username == null) {
            throw new InvalidDataExeception("[username] must not null!");
        }
        return userRepository.findByUsername(username);
    }
}
