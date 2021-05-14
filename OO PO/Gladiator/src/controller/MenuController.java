package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void startGame(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chooseMenu.fxml"));
            loader. load();
            ((ChooseMenuController)loader.getController()).setStage(stage);
            stage.setScene(new Scene(loader.getRoot(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void aboutGame(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aboutGame.fxml"));
            loader.load();
            ((AboutGameController)loader.getController()).setStage(stage);
            stage.setScene(new Scene(loader.getRoot(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
