package com.personal.story.layer.application.validate.impl;

import com.personal.story.layer.application.entity.User;
import com.personal.story.layer.application.exception.InvalidDataExeception;
import com.personal.story.layer.application.validate.IValidable;

public class UserValidator implements IValidable<User> {
    @Override
    public void validate(User user) throws InvalidDataExeception {
        if (user == null) {
            throw new InvalidDataExeception("User must not null");
        }
        if (user.getUsername() == null) {
            throw new InvalidDataExeception("Field [username] must be required!");
        }
        if (user.getPassword() == null) {
            throw new InvalidDataExeception("Field [password] must be required!");
        }
    }
}
