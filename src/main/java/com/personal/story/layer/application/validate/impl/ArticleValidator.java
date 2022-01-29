package com.personal.story.layer.application.validate.impl;

import com.personal.story.layer.application.entity.Article;
import com.personal.story.layer.application.exception.InvalidDataExeception;
import com.personal.story.layer.application.validate.IValidable;


public class ArticleValidator implements IValidable<Article> {

    @Override
    public void validate(Article article) throws InvalidDataExeception {
        if(article == null){
            throw new InvalidDataExeception("article must not null");
        }
        if(article.getAuthor() == null){
            throw new InvalidDataExeception("Field [author] is required!");
        }
        if(article.getCode() == null){
            throw new InvalidDataExeception("Field [code] is required!");
        }
        if(article.getTittle() == null){
            throw new InvalidDataExeception("Field [tittle] is required!");
        }
        if(article.getView() < 0){
            throw new InvalidDataExeception("Field [view] must greater than zero!");
        }
    }
}
