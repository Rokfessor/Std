package com.mvarlamov.Music.dao;

import com.mvarlamov.Music.Utils.Utils;
import com.mvarlamov.Music.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.rmi.CORBA.Util;
import java.util.List;

public class ArtistDAO {
    @Autowired
    JdbcTemplate template;

    @Nullable
    public Artist getById(long id) {
        try {
            return template.queryForObject(
                    "SELECT * FROM ARTIST WHERE ID = ?",
                    artistRowMapper(),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Artist> getByName(String name) {
        return template.query(
                "SELECT * FROM ARTIST WHERE NAME = ?",
                artistRowMapper(),
                name
        );
    }

    public List<Artist> getAll() {
        return template.query(
                "SELECT * FROM ARTIST",
                artistRowMapper()
        );
    }

    public void add(Artist artist) {
        template.update(
                "INSERT INTO ARTIST(id, name, description, imageName) VALUES (?,?,?,?)",
                artist.getId(),
                artist.getName(),
                artist.getDescription(),
                artist.getImagePath().toString()
        );
    }

    @Bean
    public RowMapper<Artist> artistRowMapper() {
        return (rs, rowNum) -> new Artist()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setImagePath(Utils.createImagePath(rs.getString("imageName")));
    }
}
