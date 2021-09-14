package org.maxim.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Parser {
    //Утилитарный парсер класс
    public static List<String> parse(String path, String separator){
        StringBuilder line = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(new File(path).toPath())){
            while (reader.ready())
                line.append(reader.readLine()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(line.toString().split(separator));
    }
}
