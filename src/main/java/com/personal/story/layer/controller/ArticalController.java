package com.personal.story.layer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.personal.story.layer.application.entity.Article;
import com.personal.story.layer.application.model.Content;
import com.personal.story.layer.application.service.IArticleService;
import com.personal.story.utils.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticalController {
    private static Logger logger = LoggerFactory.getLogger(ArticalController.class);

    @Autowired
    private IArticleService articleService;

    @GetMapping(value = "")
    public ResponseEntity<?> getList() {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAll());
        // return Response.format(articleService.getAll(), HttpStatus.OK, "done");
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveArticle(@RequestBody Article article) {
        try {
            article.setCreatedAt(System.currentTimeMillis());
            Article result = articleService.save(article);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("cant save article!", e);
            return Response.format(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticleById(@PathVariable("id") int id) {
        try {
            Article a = articleService.getById(id);
            System.out.println(a.getAuthor().getRoles());
            return ResponseEntity.status(HttpStatus.OK).body(articleService.getById(id));
        } catch (Throwable e) {
            logger.error("number format not supported!", e);
            String message = "number format not supported!";
            return Response.format(null, HttpStatus.BAD_REQUEST, message);
        }
    }


}
