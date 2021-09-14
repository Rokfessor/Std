package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    Stage stage;
    @FXML
    VBox vBox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBox.setStyle("-fx-background-image: url('images/fonMain.jpg'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
    }
}
