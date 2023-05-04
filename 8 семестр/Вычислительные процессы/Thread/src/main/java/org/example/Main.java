package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(); //Создаем загрузчик разметки
            URL fxmlUrl = getClass().getResource("/App.fxml"); //Создаем ссылку на разметку
            loader.setLocation(fxmlUrl);
            Scene root = loader.load(); //Загружаем файл разметки окна
            stage.setScene(root); //Устанавливаем загруженную разметку на окно
            stage.setResizable(false); //Убираем возможноть менять размер окна
            stage.setTitle("Cockroach runs"); //Ставит заголовок окна
            stage.show(); //Показываем окно
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}