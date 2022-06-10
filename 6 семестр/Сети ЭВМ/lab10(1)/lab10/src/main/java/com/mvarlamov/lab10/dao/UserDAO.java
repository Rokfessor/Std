package org.mvarlamov.dao;

import org.mvarlamov.model.Role;
import org.mvarlamov.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.util.List;

public class UserDAO {
    private final JdbcTemplate db;

    public UserDAO(JdbcTemplate db) {
        this.db = db;
    }

    public void create(User user) {
        db.update("INSERT INTO User VALUES (?,?,?,?)", user.getName(), user.getLogin(), user.getPassword(), user.getRole().name());
    }

    public User getUserById(int id) {
        List<User> result = db.query(
                "SELECT * FROM User WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class),
                id);

        if (result.isEmpty())
            return null;

        return result.get(0);
    }

    public User getUserByLogin(String login) {
        List<User> res = db.query("SELECT * FROM User WHERE login = ?", userRowMapper(), login);

        if (res.isEmpty())
            return null;

        return res.get(0);
    }

    public User getUserBySessionToken(String token) {
        List<User> result = db.query(
                "SELECT * FROM User WHERE login = (SELECT userLogin FROM Session WHERE token = ?)",
                new BeanPropertyRowMapper<>(User.class),
                token);

        if (result.isEmpty())
            return null;

        return result.get(0);
    }

    @Bean
    public RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getString("name"),
                rs.getString("login"),
                rs.getString("password"),
                Role.valueOf(rs.getString("role")));
    }

    @Bean
    public SingleColumnRowMapper<List<User>> userSingleRowMapper() {
        return new SingleColumnRowMapper<>();
    }
}
