package com.personal.story.config;

public class Settings {
    public int MYSQL_ARTICLE_PORT = 3306;
    public String MYSQL_ARTICLE_DBNAME = "mydb";
    public String MYSQL_ARTICLE_USERNAME = "root";
    public String MYSQL_ARTICLE_PASSWORD = "20112001";
    public int MYSQL_ARTICLE_POOL_SIZE = 5;

    private Settings() {

    }

    public static Settings getInstance() {
        return new Settings();
    }
}
