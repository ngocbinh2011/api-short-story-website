package com.personal.story.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Settings {
    private static Logger logger = LoggerFactory.getLogger(Settings.class);

    public int SERVER_PORT = 8081;

    public int MYSQL_ARTICLE_PORT = 3306;
    public String MYSQL_ARTICLE_DBNAME = "mydb";
    public String MYSQL_ARTICLE_USERNAME = "root";
    public String MYSQL_ARTICLE_PASSWORD = "20112001";
    public int MYSQL_ARTICLE_POOL_SIZE = 5;

    private static Settings instance;

    private Settings() {

    }

    public synchronized static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            File enviroment = new File("enviroment.yaml");

            if (!enviroment.exists()) {
                logger.info("Server running with default enviroment...!");
            } else {
                if (!enviroment.isDirectory()) {
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(enviroment);
                        StringBuilder stringBuilder = new StringBuilder();
                        while(scanner.hasNextLine()){
                            stringBuilder.append(scanner.nextLine());
                            stringBuilder.append("\n");
                        }
                        instance = objectMapper.readValue(enviroment, Settings.class);
                        logger.info("$$$$$Server overriding enviroment settings:$$$$$");
                        logger.info(stringBuilder.toString());
                        return instance;
                    } catch (IOException e) {
                        logger.error("Error when load custome enviroment!");
                        logger.info("Server will run with default enviroment...!");
                    } finally {
                        scanner.close();
                    }
                }
            }
            return new Settings();
        }
        return instance;
    }
}
