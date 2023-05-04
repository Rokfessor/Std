package org.example.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static void lockFile(Path p, String name) {
        try {
            if (!Files.exists(p))
                Files.createFile(p);
            Files.writeString(p, "Locked by " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unlockFile(Path p) {
        try {
            Files.writeString(p, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileIsLocked(Path p, String name) {
        try {
            String s = Files.readString(p);
            System.err.println(s);
            return s != null && !s.isBlank() && !s.contains(name);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
