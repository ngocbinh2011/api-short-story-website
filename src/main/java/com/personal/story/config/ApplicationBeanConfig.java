package com.personal.story.config;

import com.personal.story.layer.application.entity.Article;
import com.personal.story.layer.application.entity.User;
import com.personal.story.layer.application.validate.IValidable;
import com.personal.story.layer.application.validate.impl.ArticleValidator;
import com.personal.story.layer.application.validate.impl.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfig {

    @Bean("articleValidator")
    public IValidable<Article> articleIValidatorBean(){
        return new ArticleValidator();
    }

    @Bean("userValidator")
    public IValidable<User> userIValidatorBean(){
        return new UserValidator();
    }
}
