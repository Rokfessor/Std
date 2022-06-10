package org.mvarlamov.dao;

import org.mvarlamov.config.FilesStorageService;
import org.mvarlamov.model.Comment;
import org.mvarlamov.model.Dish;
import org.mvarlamov.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import java.util.List;

public class DishDAO {
    private final JdbcTemplate db;
    private final TagDAO tagDAO;

    @Autowired
    public CommentDAO commentDAO;
    @Autowired
    FilesStorageService storageService;

    public DishDAO(JdbcTemplate db, TagDAO tagDAO) {
        this.db = db;
        this.tagDAO = tagDAO;
    }

    public void updateDish(Dish dish) {
        db.update("UPDATE Dish SET title = ?, anons = ?, text = ?, imagePath= ? WHERE id = ?",
                dish.getTitle(),
                dish.getAnons(),
                dish.getText(),
                dish.getImage() == null ? "" : "path",
                dish.getId()
        );

        int id = getLastDishId();

        if (dish.getTags() != null)
            dish.getTags().forEach(tag -> {
                tag.setDishId(id);
                tagDAO.createTag(tag);
            });
    }

    public void createDish(Dish dish) {
        String path = "";
        if (dish.getImage() != null)
            path = storageService.save(dish.getImage(), dish.getImage().getOriginalFilename());

        dish.setDateTime(System.currentTimeMillis());
        db.update("INSERT INTO Dish(title, dateTime, anons, text, imagePath) values(?,?,?,?,?)",
                dish.getTitle(),
                dish.getDateTime().getTime(),
                dish.getAnons(),
                dish.getText(),
                dish.getImage() == null ? "" : path
        );

        int id = getLastDishId();

        if (dish.getTags() != null)
            dish.getTags().forEach(tag -> {
                tag.setDishId(id);
                tagDAO.createTag(tag);
            });
    }

    public boolean exists(int id) {
        return db.queryForObject("SELECT id FROM Dish WHERE id = ? ",
                new SingleColumnRowMapper<>(),
                id
        ) != null;
    }

    public void deleteDish(int id) {
        db.update("DELETE FROM Dish  WHERE id = ?",
                id
        );
    }

    public Dish get(int id) {
        return db.queryForObject("SELECT * FROM Dish WHERE id = ? ",
                new BeanPropertyRowMapper<>(Dish.class),
                id
        );
    }

    public List<Dish> getList() {
        return db.query("SELECT * FROM Dish",
                new BeanPropertyRowMapper<>(Dish.class)
        );
    }

    public int getLastDishId() {
        Integer id = db.queryForObject("SELECT id FROM Dish ORDER BY ID DESC LIMIT 1", new SingleColumnRowMapper<>(Integer.class));
        if (id != null)
            return id;
        return 1;
    }

    public List<Dish> getDishByTag(Tag tag) {
        List<Dish> res = db.query("SELECT * FROM Dish LEFT JOIN Tag ON Tag.dishId = Dish.id AND Tag.text = ? ",
                new BeanPropertyRowMapper<>(Dish.class),
                tag.getText());

        for (Dish dish : res) {
            List<Tag> tags = tagDAO.getTags(dish);
            if (!tags.isEmpty())
                dish.setTags(tags.toString());
            List<Comment> comments = commentDAO.getComments(dish);
            if (!comments.isEmpty())
                dish.setComments(comments);
        }


        return res;

    }

}
