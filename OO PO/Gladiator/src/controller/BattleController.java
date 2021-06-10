package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.actions.Attack;
import model.gamemanager.GameManager;
import model.utils.Utils;

public class BattleController {
    public ProgressBar player1HealthBar;
    public ProgressBar player2HealthBar;
    public ProgressBar player1GreavesBar;
    public ProgressBar player1ArmorBar;
    public ProgressBar player1HelmetBar;
    public ProgressBar player1ShieldBar;
    public ProgressBar player2ShieldBar;
    public ProgressBar player2HelmetBar;
    public ProgressBar player2ArmorBar;
    public ProgressBar player2GreavesBar;
    public Label player1WeaponDamage;
    public Label player2WeaponDamage;
    public Label player1Damage;
    public Label player2Damage;
    public ScrollPane scrollPane;

    @FXML
    public GladiatorAnimationController gladiatorAnimation1Controller;
    @FXML
    public GladiatorAnimationController gladiatorAnimation2Controller;
    public VBox vBox;

    Stage stage;
    GameManager gameManager = GameManager.getInstance();
    boolean turn = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        update();
        vBox.setStyle("-fx-background-image: url('images/fonBattle.png'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        gladiatorAnimation1Controller.init(true);
        gladiatorAnimation2Controller.init(false);
    }

    public void update() {
        if (gameManager.gladiator1.health <= 0 && gameManager.gladiator2.health <= 0) {
            Utils.showAlert("Оба гладиатора умерли. Ничья!");
        } else if (gameManager.gladiator1.health <= 0) {
            Utils.showAlert(gameManager.gladiator2.name + " победил!");
        } else if (gameManager.gladiator2.health <= 0)
            Utils.showAlert(gameManager.gladiator1.name + " победил!");
        else {
            player1Damage.setText(gameManager.gladiator1.damage + "");
            player2Damage.setText(gameManager.gladiator2.damage + "");

            player1HealthBar.setProgress(gameManager.gladiator1.health / gameManager.gladiator1.maxHealth);
            player2HealthBar.setProgress(gameManager.gladiator2.health / gameManager.gladiator2.maxHealth);

            if (gameManager.gladiator1.weapon != null)
                player1WeaponDamage.setText(gameManager.gladiator1.weapon.damage + "");
            if (gameManager.gladiator2.weapon != null)
                player2WeaponDamage.setText(gameManager.gladiator2.weapon.damage + "");

            if (gameManager.gladiator1.helmet != null) {
                player1HelmetBar.setProgress(gameManager.gladiator1.helmet.defend);
            }

            if (gameManager.gladiator2.helmet != null) {
                player2HelmetBar.setProgress(gameManager.gladiator2.helmet.defend);
            }

            if (gameManager.gladiator1.armor != null) {
                player1ArmorBar.setProgress(gameManager.gladiator1.armor.defend);
            }

            if (gameManager.gladiator2.armor != null) {
                player2ArmorBar.setProgress(gameManager.gladiator2.armor.defend);
            }

            if (gameManager.gladiator1.greaves != null) {
                player1GreavesBar.setProgress(gameManager.gladiator1.greaves.defend);
            }

            if (gameManager.gladiator2.greaves != null) {
                player2GreavesBar.setProgress(gameManager.gladiator2.greaves.defend);
            }
        }

        printAction("  ");
    }

    public void printAction(String action) {
        ((VBox) scrollPane.getContent()).getChildren().add(new Label(action));
        scrollPane.setVvalue(1D);
    }

    public void player1Attack(ActionEvent actionEvent) {
        if (!turn) {
            gameManager.gladiator1.doAttack(gameManager.gladiator2, Attack.ARM, Attack.BODY_ATTACK);
            gladiatorAnimation1Controller.animateArmAttack();
            gladiatorAnimation2Controller.animateGetAttack();
            update();
            turn = !turn;
        } else {
            Utils.showAlert("Сейчас не ваш ход!");
        }
    }

    public void player1WeaponAttack(ActionEvent actionEvent) {
        if (!turn) {
            if (gameManager.gladiator1.weapon != null) {
                gameManager.gladiator1.doAttack(gameManager.gladiator2, Attack.WEAPON, Attack.BODY_ATTACK);
                gladiatorAnimation1Controller.animateWeaponAttack();
                gladiatorAnimation2Controller.animateGetAttack();
                update();
                turn = !turn;
            } else
                Utils.showAlert("У вас нет оружия!");
        } else {
            Utils.showAlert("Сейчас не ваш ход!");
        }
    }

    public void player2WeaponAttack(ActionEvent actionEvent) {
        if (turn) {
            if (gameManager.gladiator2.weapon != null) {
                gameManager.gladiator2.doAttack(gameManager.gladiator1, Attack.WEAPON, Attack.BODY_ATTACK);
                gladiatorAnimation2Controller.animateWeaponAttack();
                gladiatorAnimation1Controller.animateGetAttack();
                update();
                turn = !turn;
            } else
                Utils.showAlert("У вас нет оружия!");
        } else {
            Utils.showAlert("Сейчас не ваш ход!");
        }
    }

    public void player2Attack(ActionEvent actionEvent) {
        if (turn) {
            gameManager.gladiator2.doAttack(gameManager.gladiator1, Attack.ARM, Attack.BODY_ATTACK);
            gladiatorAnimation2Controller.animateArmAttack();
            gladiatorAnimation1Controller.animateGetAttack();
            update();
            turn = !turn;
        } else {
            Utils.showAlert("Сейчас не ваш ход!");
        }
    }
}
