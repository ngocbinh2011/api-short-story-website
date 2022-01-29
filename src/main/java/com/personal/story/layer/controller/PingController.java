package com.personal.story.layer.controller;

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
    @Qualifier("mysqlArticleDatasource")
    DataSource dataSource;

    @GetMapping("/ping")
    public String ping() throws SQLException {


        return "ping success";
    }
}
