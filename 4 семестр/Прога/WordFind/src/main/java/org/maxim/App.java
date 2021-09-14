package org.maxim;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.maxim.model.searchEngine.SearchEngine;
import org.maxim.utils.FXMLLoader;
import org.maxim.utils.Parser;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    public static void main(String[] args) {
        initWordsBase(); //Инициализация базы слов
        launch(); //Инициализация приложения
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(FXMLLoader.loadFXML("window"));
        stage.setScene(scene);
        stage.setTitle("Word find");
        stage.show();
    }

    private static void initWordsBase() {
        SearchEngine searchEngine = SearchEngine.getInstance();
        searchEngine.setWords(Parser.parse("src/main/resources/words.txt", "\n"));
    }
}