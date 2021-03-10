package org.maxim.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.maxim.model.searchEngine.SearchEngine;
import org.maxim.utils.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Window implements Initializable {
    public TextField textField;
    public Button button;
    public TextArea textArea;
    private final SearchEngine searchEngine = new SearchEngine();
    public Button editWord;

    public void findWords(ActionEvent actionEvent) {
        //Обработка нажатия кнопки поиска слов по маске
        List<String> words = new ArrayList<>();
        try {
            words = searchEngine.findByMask(textField.getText());
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Неверно введенное слово");
        }
        textArea.clear();
        if (words.size() == 0) {
            textArea.setText("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Слов по данной маске не найдено");
            alert.show();
        } else {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word).append("\n");
            }
            textArea.setText(sb.toString());
        }
    }

    public void editWord(ActionEvent actionEvent) throws IOException {
        //Обработка нажатия кнопки добавления\удаления слов из базы
        Stage stage = new Stage();
        stage.setMinHeight(120);
        stage.setMinWidth(220);
        stage.setTitle("Edit");
        Scene scene = new Scene(FXMLLoader.loadFXML("wordsEditor"));
        stage.setScene(scene);
        stage.setHeight(50);
        stage.setWidth(100);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setEditable(false);
    }
}
