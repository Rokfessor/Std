package org.maxim.utils;

import javafx.scene.Parent;
import org.maxim.App;

import java.io.IOException;

public class FXMLLoader {
    //Утилитарный класс загрузки "fxml файлов"
    public static Parent loadFXML(String fxml) throws IOException {
        javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(App.class.getResource("/org/maxim/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
