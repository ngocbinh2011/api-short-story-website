package com.personal.story.layer.application.service.impl;

import com.personal.story.layer.application.entity.Article;
import com.personal.story.layer.application.exception.InvalidDataExeception;
import com.personal.story.layer.application.repository.ArticleRepository;
import com.personal.story.layer.application.service.IArticleService;
import com.personal.story.layer.application.validate.IValidable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    @Qualifier("articleValidator")
    private IValidable<Article> articleValidator;

    @Override
    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    @Override
    public Article save(Article article) throws InvalidDataExeception {
        articleValidator.validate(article);
        return articleRepository.save(article);
    }


}
