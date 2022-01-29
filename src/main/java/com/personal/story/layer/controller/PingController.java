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

    @GetMapping("/ping")
    public String ping() throws SQLException {
        return "ping success";
    }
}
