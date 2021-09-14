package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.gamemanager.GameManager;
import model.gladiator.Gladiator;
import model.gladiator.GladiatorManager;
import model.protection.Protection;
import model.protection.ProtectionManager;
import model.weapon.Weapon;
import model.weapon.WeaponManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseMenuController implements Initializable {
    public VBox vBox;
    GameManager gameManager = GameManager.getInstance();

    public ImageView player1Gladiator;
    public ImageView player1Helmet;
    public ImageView player1Armor;
    public ImageView player1Greaves;
    public ImageView player1Weapon;
    public ImageView player1Shield;

    public ImageView player2Gladiator;
    public ImageView player2Helmet;
    public ImageView player2Armor;
    public ImageView player2Greaves;
    public ImageView player2Weapon;
    public ImageView player2Shield;

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

    private boolean gladiator1Set = false;
    private boolean gladiator2Set = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBox.setStyle("-fx-background-image: url('images/fonChoose.jpg'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");


        Tooltip tooltipGladiator1 = new Tooltip("Гопломах\nУрон: 5\nЖизни: 100\nУбирает 5 единиц урона у оружия. Действует 5 ходов.");
        tooltipGladiator1.setShowDelay(Duration.ZERO);
        Tooltip.install(gladiator1, tooltipGladiator1);

        Tooltip tooltipGladiator2 = new Tooltip("Тракс\nУрон: 3\nЖизни: 80\nВосстанавливает себе 10 жизней.");
        tooltipGladiator2.setShowDelay(Duration.ZERO);
        Tooltip.install(gladiator2, tooltipGladiator2);

        Tooltip tooltipGladiator3 = new Tooltip("Спартак\nУрон: 5\nЖизни: 120\nУбирает себе и врагу 10 жизней");
        tooltipGladiator3.setShowDelay(Duration.ZERO);
        Tooltip.install(gladiator3, tooltipGladiator3);

        Tooltip tooltipGladiator4 = new Tooltip("Секутор\nУрон: 7\nЖизни: 100\n");
        tooltipGladiator4.setShowDelay(Duration.ZERO);
        Tooltip.install(gladiator4, tooltipGladiator4);


        Tooltip tooltipWeapon1 = new Tooltip("Меч\nУрон: 15\nКоэфициент разрушаемости: 1\nШанс промаха: 0.65");
        tooltipWeapon1.setShowDelay(Duration.ZERO);
        Tooltip.install(weapon1, tooltipWeapon1);

        Tooltip tooltipWeapon2 = new Tooltip("Топор\nУрон: 12\nКоэфициент разрушаемости: 1\nШанс промаха: 0.5");
        tooltipWeapon2.setShowDelay(Duration.ZERO);
        Tooltip.install(weapon2, tooltipWeapon2);

        Tooltip tooltipWeapon3 = new Tooltip("Копьё\nУрон: 7\nКоэфициент разрушаемости: 1\nШанс промаха: 0.2");
        tooltipWeapon3.setShowDelay(Duration.ZERO);
        Tooltip.install(weapon3, tooltipWeapon3);

        Tooltip tooltipWeapon4 = new Tooltip("Трезубец\nУрон: 10\nКоэфициент разрушаемости: 1\nШанс промаха: 0.25");
        tooltipWeapon4.setShowDelay(Duration.ZERO);
        Tooltip.install(weapon4, tooltipWeapon4);


        Tooltip tooltipHelmet1 = new Tooltip("Блэйд шлем\nКоэфициент разрушаемости: 0.15\nВозвращает врагу половину полученного урона.");
        tooltipHelmet1.setShowDelay(Duration.ZERO);
        Tooltip.install(helmet1, tooltipHelmet1);

        Tooltip tooltipHelmet2 = new Tooltip("Кирас шлем\nКоэфициент разрушаемости: 0.1.");
        tooltipHelmet2.setShowDelay(Duration.ZERO);
        Tooltip.install(helmet2, tooltipHelmet2);

        Tooltip tooltipHelmet3 = new Tooltip("Плэйт шлем\nКоэфициент разрушаемости: 0.1\nУвеличивает урон оружия/руки на 0.5.");
        tooltipHelmet3.setShowDelay(Duration.ZERO);
        Tooltip.install(helmet3, tooltipHelmet3);

        Tooltip tooltipHelmet4 = new Tooltip("Тараск шлем\nКоэфициент разрушаемости: 0.2\nВосстанавливает 15 здоровья.");
        tooltipHelmet4.setShowDelay(Duration.ZERO);
        Tooltip.install(helmet4, tooltipHelmet4);


        Tooltip tooltipArmor1 = new Tooltip("Блэйд нагрудник\nКоэфициент разрушаемости: 0.15\nВозвращает врагу половину полученного урона.");
        tooltipArmor1.setShowDelay(Duration.ZERO);
        Tooltip.install(armor1, tooltipArmor1);

        Tooltip tooltipArmor2 = new Tooltip("Кирас нагрудник\nКоэфициент разрушаемости: 0.1.");
        tooltipArmor2.setShowDelay(Duration.ZERO);
        Tooltip.install(armor2, tooltipArmor2);

        Tooltip tooltipArmor3 = new Tooltip("Плэйт нагрудник\nКоэфициент разрушаемости: 0.1\nУвеличивает урон оружия/руки на 0.5.");
        tooltipArmor3.setShowDelay(Duration.ZERO);
        Tooltip.install(armor3, tooltipArmor3);

        Tooltip tooltipArmor4 = new Tooltip("Тараск нагрудник\nКоэфициент разрушаемости: 0.2\nВосстанавливает 15 здоровья.");
        tooltipArmor4.setShowDelay(Duration.ZERO);
        Tooltip.install(armor4, tooltipArmor4);


        Tooltip tooltipGreaves1 = new Tooltip("Блэйд поножи\nКоэфициент разрушаемости: 0.15\nВозвращает врагу половину полученного урона.");
        tooltipGreaves1.setShowDelay(Duration.ZERO);
        Tooltip.install(greaves1, tooltipGreaves1);

        Tooltip tooltipGreaves2 = new Tooltip("Кирас поножи\nКоэфициент разрушаемости: 0.1.");
        tooltipGreaves2.setShowDelay(Duration.ZERO);
        Tooltip.install(greaves2, tooltipGreaves2);

        Tooltip tooltipGreaves3 = new Tooltip("Плэйт поножи\nКоэфициент разрушаемости: 0.1\nУвеличивает урон оружия/руки на 0.5.");
        tooltipGreaves3.setShowDelay(Duration.ZERO);
        Tooltip.install(greaves3, tooltipGreaves3);

        Tooltip tooltipGreaves4 = new Tooltip("Тараск поножи\nКоэфициент разрушаемости: 0.2\nВосстанавливает 15 здоровья.");
        tooltipGreaves4.setShowDelay(Duration.ZERO);
        Tooltip.install(greaves4, tooltipGreaves4);
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
        if (gladiator1Set && gladiator2Set) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/battle.fxml"));
                loader.load();
                BattleController battleController = loader.getController();
                battleController.setStage(stage);
                gameManager.setBattleController(battleController);
                battleController.init();
                stage.setScene(new Scene(loader.getRoot(), 1280, 720));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Гладиатор не выбран");
            alert.show();
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

            if ((imageView == player1Helmet || imageView == player2Helmet) && dragEvent.getDragboard().getString().contains("helmet") && (gladiator1Set && gladiator2Set)) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Armor || imageView == player2Armor) && dragEvent.getDragboard().getString().contains("armor") && (gladiator1Set && gladiator2Set)) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Greaves || imageView == player2Greaves) && dragEvent.getDragboard().getString().contains("greaves") && (gladiator1Set && gladiator2Set)) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
                dragEvent.consume();
                return;
            }

            if ((imageView == player1Weapon || imageView == player2Weapon) && dragEvent.getDragboard().getString().contains("weapon") && (gladiator1Set && gladiator2Set)) {
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
        String type = dragEvent.getDragboard().getString();
        ImageView imageView = ((ImageView) dragEvent.getSource());
        Image image = new Image("/sprites/" + type + ".png");
        imageView.setImage(image);
        dragEvent.setDropCompleted(true);
        dragEvent.consume();

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
            Weapon weapon = WeaponManager.getWeapon(type);

            if (imageView == player1Weapon) {
                gameManager.setPlayer1Weapon(weapon, type);
            }
            if (imageView == player2Weapon) {
                gameManager.setPlayer2Weapon(weapon, type);
            }

        } else if (type.contains("gladiator")) {
            Gladiator gladiator = GladiatorManager.getGladiator(type);
            System.err.println(type);

            if (imageView == player1Gladiator) {
                gameManager.setPlayer1Gladiator(gladiator, type);
                gladiator1Set = true;
            }
            if (imageView == player2Gladiator) {
                gameManager.setPlayer2Gladiator(gladiator, type);
                gladiator2Set = true;
            }
        }
    }
}
