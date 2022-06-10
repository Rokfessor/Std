package com.mvarlamov.Music;

import com.mvarlamov.Music.dao.ArtistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    @Autowired
    ArtistDAO artistDAO;

    @Override
    public void run(String... args) throws Exception {
        System.err.println(artistDAO.getAll());
    }
}
