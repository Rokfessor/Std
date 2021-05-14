package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutGameController {
    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
            loader.load();
            ((MenuController)loader.getController()).setStage(stage);
            stage.setScene(new Scene(loader.getRoot(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
