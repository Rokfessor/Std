package com.mvarlamov.Music.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class Utils {
    @Autowired
    private static String hostPath;
    @Value("imagesPath")
    private static String imagesPath;

    public static URI createImagePath(String imageName) {
        if (imageName == null || imageName.isEmpty())
            return null;

        return URI.create(
                String.format(
                        "%s/%s/%s",
                        hostPath,
                        imagesPath,
                        imageName
                )
        );
    }
}
