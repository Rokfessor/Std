package com.mvarlamov.Music.dao;

import com.mvarlamov.Music.model.Session;
import com.mvarlamov.Music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class SessionDAO {
    @Autowired
    JdbcTemplate template;

    public Session create(User user) {
        logout(user);
        Session session = new Session();
        session.setLogin(user.getLogin());
        session.setToken(UUID.randomUUID().toString());

        template.update(
                "INSERT INTO Session (login, token) VALUES (?, ?)",
                session.getLogin(),
                session.getToken()
        );

        return session;
    }

    public void logout(User user) {
        template.update(
                "DELETE * FROM Session WHERE login = ?",
                user.getLogin()
        );
    }

}
