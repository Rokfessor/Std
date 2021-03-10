package org.maxim.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.maxim.model.searchEngine.SearchEngine;

public class WordsEditor {
    public TextField textField;
    public Button removeButton;
    public Button addButton;
    private final SearchEngine searchEngine = SearchEngine.getInstance();

    public void removeWord(ActionEvent actionEvent) {
        //Обработка нажатия кнопки удаления слов
        String word = textField.getText();
        System.err.println(searchEngine.findByMask(word).size());
        if (searchEngine.findByMask(word).size() >= 1) {
            searchEngine.removeWord(word);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Слово \"" + word + "\" удалено из базы");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Слова нет в базе");
            alert.show();
        }
    }

    public void addWord(ActionEvent actionEvent) {
        //Обработка нажатия кнопки добавления слов
        String word = textField.getText();

        if (searchEngine.findByMask(word).size() == 0) {
            searchEngine.addWord(word);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Слово \"" + word + "\" добавлено в базу");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Слово уже есть в базе");
            alert.show();
        }
    }
}
