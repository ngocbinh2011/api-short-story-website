package com.personal.story.layer.application.repository;

import com.personal.story.layer.application.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article save(Article article);

    Article findById(int id);

}
