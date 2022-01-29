package com.personal.story.layer.application.validate;

import com.personal.story.layer.application.exception.InvalidDataExeception;

public interface IValidable <T> {
    void validate(T object) throws InvalidDataExeception;
}
