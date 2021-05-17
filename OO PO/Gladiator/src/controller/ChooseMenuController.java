package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Stage;
import model.gamemanager.GameManager;
import model.protection.Protection;
import model.protection.ProtectionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseMenuController implements Initializable {
    GameManager gameManager;

    public ImageView player1Gladiator;
    public ImageView player1Helmet;
    public ImageView player1Armor;
    public ImageView player1Greaves;
    public ImageView player1Weapon;
    public ImageView player1Shield;
    public ImageView player1Skill;

    public ImageView player2Gladiator;
    public ImageView player2Helmet;
    public ImageView player2Armor;
    public ImageView player2Greaves;
    public ImageView player2Weapon;
    public ImageView player2Shield;
    public ImageView player2Skill;

    public ImageView gladiator1;
    public ImageView gladiator2;
    public ImageView gladiator3;
    public ImageView gladiator4;

    public ImageView weapon1;
    public ImageView weapon2;
    public ImageView weapon3;
    public ImageView weapon4;

    public ImageView helmet1;
    public ImageView helmet2;
    public ImageView helmet3;
    public ImageView helmet4;

    public ImageView armor1;
    public ImageView armor2;
    public ImageView armor3;
    public ImageView armor4;

    public ImageView greaves1;
    public ImageView greaves2;
    public ImageView greaves3;
    public ImageView greaves4;

    public ImageView shield1;
    public ImageView shield2;
    public ImageView shield3;
    public ImageView shield4;

    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameManager = GameManager.getInstance();
        gameManager.newGame();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
            loader.load();
            ((MenuController) loader.getController()).setStage(stage);
            stage.setScene(new Scene(loader.getRoot(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startBattle(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/battle.fxml"));
            loader.load();
            ((BattleController) loader.getController()).setStage(stage);
            stage.setScene(new Scene(loader.getRoot(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dragDetected(MouseEvent mouseEvent) {
        ImageView imageView = ((ImageView) mouseEvent.getSource());
        Image image = new Image(imageView.getImage().getUrl(), 60, 60, false, false);
        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(image);

        {
            if (imageView.equals(gladiator1)) {
                System.err.println("!");
                content.putString("gladiator1");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(gladiator2)) {
                content.putString("gladiator2");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(gladiator3)) {
                content.putString("gladiator3");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(gladiator4)) {
                content.putString("gladiator4");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(helmet1)) {
                content.putString("helmet1");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(helmet2)) {
                content.putString("helmet2");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(helmet3)) {
                content.putString("helmet3");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(helmet4)) {
                content.putString("helmet4");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(armor1)) {
                content.putString("armor1");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(armor2)) {
                content.putString("armor2");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(armor3)) {
                content.putString("armor3");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(armor4)) {
                content.putString("armor4");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(greaves1)) {
                content.putString("greaves1");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(greaves2)) {
                content.putString("greaves2");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(greaves3)) {
                content.putString("greaves3");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(greaves4)) {
                content.putString("greaves4");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(weapon1)) {
                content.putString("weapon1");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(weapon2)) {
                content.putString("weapon2");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(weapon3)) {
                content.putString("weapon3");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(weapon4)) {
                content.putString("weapon4");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(shield1)) {
                content.putString("shield1");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(shield2)) {
                content.putString("shield2");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(shield3)) {
                content.putString("shield3");
                dragboard.setContent(content);
                return;
            }

            if (imageView.equals(shield4)) {
                content.putString("shield4");
                dragboard.setContent(content);
            }
        }
    }

    public void dragOver(DragEvent dragEvent) {
        ImageView imageView = (ImageView) dragEvent.getSource();

        if (imageView.getImage() == null) {
            if ((imageView == player1Gladiator || imageView == player2Gladiator) && dragEvent.getDragboard().getString().contains("gladiator")) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Helmet || imageView == player2Helmet) && dragEvent.getDragboard().getString().contains("helmet")) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Armor || imageView == player2Armor) && dragEvent.getDragboard().getString().contains("armor")) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Greaves || imageView == player2Greaves) && dragEvent.getDragboard().getString().contains("greaves")) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Weapon || imageView == player2Weapon) && dragEvent.getDragboard().getString().contains("weapon")) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Shield || imageView == player2Shield) && dragEvent.getDragboard().getString().contains("shield")) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
            }
        }
    }

    public void dragDropped(DragEvent dragEvent) {
        ImageView imageView = ((ImageView) dragEvent.getSource());
        Image image = dragEvent.getDragboard().getImage();
        imageView.setImage(image);
        System.err.println(dragEvent.getDragboard().getString());
        dragEvent.setDropCompleted(true);
        dragEvent.consume();

        String type = dragEvent.getDragboard().getString();

        if (type.contains("armor") || type.contains("greaves") || type.contains("helmet")) {
            Protection protection = ProtectionManager.getProtection(type);

            if (imageView == player1Helmet)
                gameManager.setPlayer1Helmet(protection, type);

            if (imageView == player1Armor)
                gameManager.setPlayer1Armor(protection, type);

            if (imageView == player1Greaves)
                gameManager.setPlayer1Greaves(protection, type);

            if (imageView == player1Shield)
                gameManager.setPlayer2Armor(protection, type);

            if (imageView == player2Helmet)
                gameManager.setPlayer2Helmet(protection, type);

            if (imageView == player2Armor)
                gameManager.setPlayer2Armor(protection, type);

            if (imageView == player2Greaves)
                gameManager.setPlayer2Greaves(protection, type);

            if (imageView == player2Shield)
                gameManager.setPlayer2Armor(protection, type);

        } else if (type.contains("weapon")) {

            if (imageView == player1Weapon) {
            }

            if (imageView == player2Weapon) {
            }

        } else if (type.contains("gladiator")) {

            if (imageView == player1Gladiator) {
            }
            if (imageView == player2Gladiator) {
            }
        }
    }
}
