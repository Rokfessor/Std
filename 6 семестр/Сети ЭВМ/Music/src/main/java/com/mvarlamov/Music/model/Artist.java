package com.mvarlamov.Music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mvarlamov.Music.Utils.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Artist {
    private long id;
    private String name;
    private String description;
    private URI imagePath;
    @JsonIgnore
    private MultipartFile image;
    @JsonIgnore
    @Autowired
    String hostPath;
    @JsonIgnore
    @Value("imagesPath")
    String imagesPath;

    public Artist setImage(MultipartFile image) {
        if (image == null)
            return this;

        this.image = image;
        this.imagePath = Utils.createImagePath(image.getName());

        return this;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath=" + imagePath +
                '}';
    }
}
