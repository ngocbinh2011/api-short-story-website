package com.personal.story;

import com.personal.story.config.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
@Configuration
public class ShortStoryWebsiteApplication {
    private static Logger logger = LoggerFactory.getLogger(ShortStoryWebsiteApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ShortStoryWebsiteApplication.class);
        springApplication.setDefaultProperties(
                Collections.singletonMap("server.port", String.valueOf(Settings.getInstance().SERVER_PORT))
        );

        logger.info(String.format("Server running in PORT %d", Settings.getInstance().SERVER_PORT));
        springApplication.run();
    }
}
