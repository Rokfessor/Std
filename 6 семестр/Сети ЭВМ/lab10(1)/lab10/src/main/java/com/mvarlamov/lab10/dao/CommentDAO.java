package org.mvarlamov.dao;

import org.mvarlamov.model.Comment;
import org.mvarlamov.model.Dish;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.util.List;

public class CommentDAO {
    private final JdbcTemplate db;

    public CommentDAO(JdbcTemplate db) {
        this.db = db;
    }

    public void createComment(Comment comment) {
        db.update("INSERT INTO Comment (dishId, text, userLogin) VALUES (?,?,?)",
                comment.getDishId(),
                comment.getText(),
                comment.getUserLogin()
        );
    }

    public List<Comment> getComments(Dish dish) {
        return db.query("SELECT * FROM Comment WHERE dishId = ?", new BeanPropertyRowMapper<>(Comment.class), dish.getId());
    }

    public boolean exists(int id) {
        return db.queryForObject("SELECT id FROM Comment WHERE id = ?",
                new SingleColumnRowMapper<>(),
                id
        ) != null;
    }

    public void deleteComment(int id) {
        db.update("DELETE FROM Comment WHERE id = ?",
                id
        );
    }
}
