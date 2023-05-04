package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.io.IOException;

public class NamePicker extends Dialog<String> {

    @FXML
    public TextField textField;

    public String show(Window window) {
        FXMLLoader loader = new FXMLLoader(); //Загрузчик разметки
        loader.setLocation(getClass().getResource("/NamePicker.fxml"));
        loader.setController(this);

        try {
            DialogPane pane = loader.load();
            initOwner(window); //Устанавливаем родительский компонент
            initModality(Modality.APPLICATION_MODAL); //Устанавливаем режим диалогового окна
            setResizable(true);
            setTitle("Enter your name");
            setDialogPane(pane);
            getDialogPane()
                    .getScene()
                    .getWindow()
                    .setOnCloseRequest(event -> close()); //Обрабатываем событие закрытия окна

            setResultConverter(param -> textField.getText());
            return showAndWait().get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
