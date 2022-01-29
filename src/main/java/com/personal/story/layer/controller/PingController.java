package com.personal.story.layer.controller;

import com.personal.story.layer.application.entity.Article;
import com.personal.story.layer.application.entity.User;
import com.personal.story.layer.application.model.Content;
import com.personal.story.layer.application.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class PingController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/ping")
    public String ping() throws SQLException {

        Article article = new Article();
        article.setTittle("title");
        User user = new User(); user.setId(1);
        article.setAuthor(user);
        article.setContent(new Content("this is content"));
        articleRepository.save(article);

        Article a = articleRepository.findById(3);
        System.out.println(a.getContent());


        return "ping success";
    }
}
