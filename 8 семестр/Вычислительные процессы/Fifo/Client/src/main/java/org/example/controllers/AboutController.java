package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.io.IOException;

public class AboutController  extends Dialog<Void> {
    public AboutController(Window window) {
        FXMLLoader loader = new FXMLLoader(); //Загрузчик разметки
        loader.setLocation(getClass().getResource("/About.fxml"));
        loader.setController(this);

        try {
            DialogPane pane = loader.load();
            initOwner(window); //Устанавливаем родительский компонент
            initModality(Modality.APPLICATION_MODAL); //Устанавливаем режим диалогового окна
            setResizable(true);
            setTitle("About MemWatcher");
            setDialogPane(pane);
            getDialogPane()
                    .getScene()
                    .getWindow()
                    .setOnCloseRequest(event -> closeDialog()); //Обрабатываем событие закрытия окна
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Обработчик закрытия окна
    @FXML
    public void closeDialog() {
        getDialogPane().getScene().getWindow().hide();
    }
}
