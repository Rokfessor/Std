package com.mvarlamov.Music.dao;

import com.mvarlamov.Music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAO {
    @Autowired
    JdbcTemplate template;

    public void create(User user) {
        template.update(
                "INSERT INTO User_ (login, pwdHash, role, name, age, gender) VALUES(?,?,?,?,?,?)",
                user.getLogin(),
                user.getPwdHash(),
                user.getRole().toString(),
                user.getName(),
                user.getAge(),
                user.getGender().toString()
        );
    }

    public User getByLogin(String login) {
        try {
            return template.queryForObject(
                    "SELECT * FROM User_ WHERE login = ?",
                    new BeanPropertyRowMapper<>(User.class),
                    login
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
