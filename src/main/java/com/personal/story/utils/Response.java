package com.personal.story.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    private static Logger logger = LoggerFactory.getLogger(Response.class);

    public static ResponseEntity<String> format(Object result, HttpStatus code, String message){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("result", format(result));
        json.put("message", message);
        return ResponseEntity.status(code).body(json.toString());
    }

    private static String format(Object result){
        if(result != null){
            try {
                return Util.objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                logger.error("cant convert to json format!", e);
            }
        }
        return null;
    }
}
