package org.mvarlamov.dao;

import org.mvarlamov.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class SessionDAO {
    final private JdbcTemplate db;

    public SessionDAO(JdbcTemplate db) {
        this.db = db;
    }

    public void createSession(User user, String token) {
        db.update("INSERT INTO Session (userLogin, token) VALUES (?, ?)", user.getLogin(), token);
    }
}
