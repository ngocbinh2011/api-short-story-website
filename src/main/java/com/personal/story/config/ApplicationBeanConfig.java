package com.personal.story.config;

import com.personal.story.layer.application.entity.Article;
import com.personal.story.layer.application.validate.IValidable;
import com.personal.story.layer.application.validate.impl.ArticleValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfig {

    @Bean("articleValidator")
    public IValidable<Article> articleIValidatorBean(){
        return new ArticleValidator();
    }
}
