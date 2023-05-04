package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controllers.AppController;
import org.example.controllers.NamePicker;
import org.example.model.Client;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class Main extends Application {
    private final Path FIFO_SERVER = Path.of("/home/maxim/Desktop/fifoServer");
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(); //Создаем загрузчик разметки
            URL fxmlUrl = getClass().getResource("/App.fxml"); //Создаем ссылку на разметку
            loader.setLocation(fxmlUrl);
            Scene root = loader.load(); //Загружаем файл разметки окна
            stage.setScene(root); //Устанавливаем загруженную разметку на окно
            stage.setResizable(false); //Убираем возможноть менять размер окна

            String name = new NamePicker().show(stage.getOwner());
            if (name == null || name.isBlank())
                return;

            stage.setTitle("Client " + name); //Ставит заголовок окна

            ((AppController)loader.getController()).setClient(new Client(name, FIFO_SERVER));

            stage.show(); //Показываем окно
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}