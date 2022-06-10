package com.mvarlamov.Music.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvarlamov.Music.dao.ArtistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Beans {
    @Autowired
    Environment environment;

    @Bean
    public ArtistDAO artistDAO() {
        return new ArtistDAO();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public String hostPath() {
        return String.format(
                "%s:%s",
                environment.getProperty("server.host"),
                environment.getProperty("server.port")
        );
    }
}
