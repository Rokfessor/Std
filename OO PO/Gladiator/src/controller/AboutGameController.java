package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutGameController implements Initializable {
    public VBox vBox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBox.setStyle("-fx-background-image: url('images/fonMain.jpg'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
    }

}
