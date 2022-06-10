package org.mvarlamov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Dish {
    private @Nullable int id;
    private @NotNull String title;
    private @NotNull String anons;
    private @NotNull String text;
    private @Nullable List<Tag> tags;
    private @Nullable List<Comment> comments;
    private Date dateTime;
    private @Nullable MultipartFile image;
    private @Nullable String imagePath;

    public Dish(){}

    public void setDateTime(long dateTime) {
        this.dateTime = new Date(dateTime);
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setTags(@Nullable String tags) {
        if (tags != null) {
            List<Tag> res = new ArrayList<>();
            for (String tag : tags.split(",")) {
                Tag t = new Tag();
                t.setText(tag);
                res.add(t);
            }
            this.tags = res;
        }
    }

    public void setImage(MultipartFile image) {
        if (image != null) {
            if (image.getSize() > 2097152)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image size > 2Mb");

            if (!(image.getContentType().equals(MimeTypeUtils.IMAGE_JPEG_VALUE) ||
                    image.getContentType().equals(MimeTypeUtils.IMAGE_PNG_VALUE)))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong image MimeType");

            this.image = image;
        }
    }


}
