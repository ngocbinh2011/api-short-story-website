package com.personal.story.layer.application.service;

import com.personal.story.layer.application.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IArticleService {
    List<Article> getAll();
    Article save(Article article) throws Exception;
    Article getById(int id) throws Exception;
}
