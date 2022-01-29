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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticalController {

    @Autowired
    private IArticleService articleService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getList(){
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAll());
       // return Response.format(articleService.getAll(), HttpStatus.OK, "done");
    }



}
