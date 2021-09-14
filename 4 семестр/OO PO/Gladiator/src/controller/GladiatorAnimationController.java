package controller;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.gamemanager.GameManager;
import model.gladiator.Gladiator;

public class GladiatorAnimationController {
    public Pane pane;
    private final Group head = new Group();
    private ImageView body;
    private ImageView legs;
    private ImageView weapon;
    private ImageView arm = new ImageView(new Image("/sprites/arm.png", 300, 300, false, false));
    GameManager gameManager = GameManager.getInstance();
    boolean isPlayer1;

    public void init(boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;

        Gladiator gladiator;
        if (isPlayer1)
            gladiator = gameManager.gladiator1;
        else
            gladiator = gameManager.gladiator2;

        if (gladiator.helmet != null) {
            head.getChildren().add(0, new ImageView(gladiator.helmet.image));
        }
        head.getChildren().add(0, new ImageView(gladiator.image));

        head.setTranslateX(isPlayer1 ? 60 : 130);
        head.setTranslateY(10);

        arm.setX(isPlayer1 ? 20 : 50);
        arm.setY(120);

        if (gladiator.weapon != null) {
            weapon = new ImageView(gladiator.weapon.image);
        } else
            weapon = new ImageView(new Image("sprites/emptyArm.png", 300, 300, false, false));

        weapon.setX(isPlayer1 ? 0 : 70);
        weapon.setY(120);

        if (gladiator.armor != null)
            body = new ImageView(gladiator.armor.image);
        else
            body = new ImageView(new Image("sprites/body.png", 300, 300, false, false));

        body.setX(isPlayer1 ? 0 : 70);
        body.setY(120);

        if (gladiator.greaves != null) {
            legs = new ImageView(gladiator.greaves.image);
        } else
            legs = new ImageView(new Image("sprites/legs.png", 200, 200, false, false));

        legs.setX(isPlayer1 ? 60 : 110);
        legs.setY(310);

        if (!isPlayer1) {
            legs.setScaleX(-1);
            arm.setScaleX(-1);
            body.setScaleX(-1);
            weapon.setScaleX(-1);
            head.setScaleX(-1);
        }

        pane.getChildren().addAll(legs, arm, body, head, weapon);
    }

    public void animateBody(ParallelTransition parallelTransition, Duration duration) {
        //ГОЛОВА
        RotateTransition headRotate = new RotateTransition();
        headRotate.setNode(head);
        headRotate.setByAngle(isPlayer1 ? 20 : -20);
        headRotate.setDuration(duration);
        headRotate.setAutoReverse(true);
        headRotate.setCycleCount(2);

        TranslateTransition headMove = new TranslateTransition();
        headMove.setNode(head);
        headMove.setByX(isPlayer1 ? 70 : -70);
        headMove.setByY(50);
        headMove.setDuration(duration);
        headMove.setAutoReverse(true);
        headMove.setCycleCount(2);

        parallelTransition.getChildren().add(headRotate);
        parallelTransition.getChildren().add(headMove);

        //ТЕЛО
        RotateTransition bodyRotate = new RotateTransition();
        bodyRotate.setNode(body);
        bodyRotate.setByAngle(isPlayer1 ? 20 : -20);
        bodyRotate.setDuration(duration);
        bodyRotate.setAutoReverse(true);
        bodyRotate.setCycleCount(2);

        TranslateTransition bodyMove = new TranslateTransition();
        bodyMove.setNode(body);
        bodyMove.setByX(isPlayer1 ? 30 : -30);
        bodyMove.setByY(30);
        bodyMove.setDuration(duration);
        bodyMove.setAutoReverse(true);
        bodyMove.setCycleCount(2);

        parallelTransition.getChildren().add(bodyRotate);
        parallelTransition.getChildren().add(bodyMove);

        //НОГИ
        RotateTransition legsRotate = new RotateTransition();
        legsRotate.setNode(legs);
        legsRotate.setByAngle(isPlayer1 ? 10 : -10);
        legsRotate.setDuration(duration);
        legsRotate.setAutoReverse(true);
        legsRotate.setCycleCount(2);

        parallelTransition.getChildren().add(legsRotate);
    }

    public void animateGetAttack() {
        ParallelTransition parallelTransition = new ParallelTransition();
        Duration duration = Duration.seconds(1);

        //ГОЛОВА
        RotateTransition headRotate = new RotateTransition();
        headRotate.setNode(head);
        headRotate.setByAngle(isPlayer1 ? -20 : 20);
        headRotate.setDuration(duration);
        headRotate.setAutoReverse(true);
        headRotate.setCycleCount(2);

        TranslateTransition headMove = new TranslateTransition();
        headMove.setNode(head);
        headMove.setByX(isPlayer1 ? -70 : 70);
        headMove.setByY(-10);
        headMove.setDuration(duration);
        headMove.setAutoReverse(true);
        headMove.setCycleCount(2);

        parallelTransition.getChildren().add(headRotate);
        parallelTransition.getChildren().add(headMove);

        //РУКА
        RotateTransition armRotate = new RotateTransition();
        armRotate.setNode(arm);
        armRotate.setByAngle(isPlayer1 ? -40 : 40);
        armRotate.setDuration(duration);
        armRotate.setAutoReverse(true);
        armRotate.setCycleCount(2);

        TranslateTransition armMove = new TranslateTransition();
        armMove.setNode(arm);
        armMove.setByX(isPlayer1 ? -40 : 40);
        armMove.setByY(-20);
        armMove.setDuration(duration);
        armMove.setAutoReverse(true);
        armMove.setCycleCount(2);

        parallelTransition.getChildren().add(armRotate);
        parallelTransition.getChildren().add(armMove);

        //ОРУЖИЕ
        RotateTransition weaponRotate = new RotateTransition();
        weaponRotate.setNode(weapon);
        weaponRotate.setByAngle(isPlayer1 ? -30 : 30);
        weaponRotate.setDuration(duration);
        weaponRotate.setAutoReverse(true);
        weaponRotate.setCycleCount(2);

        TranslateTransition weaponMove = new TranslateTransition();
        weaponMove.setNode(weapon);
        weaponMove.setByX(isPlayer1 ? -30 : 30);
        weaponMove.setByY(-20);
        weaponMove.setDuration(duration);
        weaponMove.setAutoReverse(true);
        weaponMove.setCycleCount(2);

        parallelTransition.getChildren().add(weaponMove);
        parallelTransition.getChildren().add(weaponRotate);

        //ТЕЛО
        RotateTransition bodyRotate = new RotateTransition();
        bodyRotate.setNode(body);
        bodyRotate.setByAngle(isPlayer1 ? -15 : 15);
        bodyRotate.setDuration(duration);
        bodyRotate.setAutoReverse(true);
        bodyRotate.setCycleCount(2);

        TranslateTransition bodyMove = new TranslateTransition();
        bodyMove.setNode(body);
        bodyMove.setByX(isPlayer1 ? -30 : 30);
        bodyMove.setByY(-10);
        bodyMove.setDuration(duration);
        bodyMove.setAutoReverse(true);
        bodyMove.setCycleCount(2);

        parallelTransition.getChildren().add(bodyRotate);
        parallelTransition.getChildren().add(bodyMove);

        //НОГИ
        RotateTransition legsRotate = new RotateTransition();
        legsRotate.setNode(legs);
        legsRotate.setByAngle(isPlayer1 ? -10 : 10);
        legsRotate.setDuration(duration);
        legsRotate.setAutoReverse(true);
        legsRotate.setCycleCount(2);

        parallelTransition.getChildren().add(legsRotate);

        parallelTransition.play();
    }

    public void animateWeaponAttack() {
        ParallelTransition parallelTransition = new ParallelTransition();
        Duration duration = Duration.seconds(1);

        animateBody(parallelTransition, duration);

        //РУКА
        RotateTransition armRotate = new RotateTransition();
        armRotate.setNode(weapon);
        armRotate.setByAngle(isPlayer1 ? 30 : -30);
        armRotate.setDuration(duration);
        armRotate.setAutoReverse(true);
        armRotate.setCycleCount(2);

        TranslateTransition armMove = new TranslateTransition();
        armMove.setNode(arm);
        armMove.setByX(isPlayer1 ? 20 : -20);
        armMove.setByY(50);
        armMove.setDuration(duration);
        armMove.setAutoReverse(true);
        armMove.setCycleCount(2);

        parallelTransition.getChildren().addAll(armMove, armRotate);

        //ОРУЖИЕ
        SequentialTransition s1 = new SequentialTransition();
        ParallelTransition p1 = new ParallelTransition();

        RotateTransition weaponRotate1 = new RotateTransition();
        weaponRotate1.setNode(weapon);
        weaponRotate1.setByAngle(isPlayer1 ? -10 : 10);
        weaponRotate1.setDuration(Duration.seconds(1));

        TranslateTransition weaponMove1 = new TranslateTransition();
        weaponMove1.setNode(weapon);
        weaponMove1.setByX(isPlayer1 ? 60 : -60);
        weaponMove1.setByY(20);
        weaponMove1.setDuration(Duration.seconds(1));

        p1.getChildren().addAll(weaponMove1, weaponRotate1);

        ParallelTransition p2 = new ParallelTransition();

        RotateTransition weaponRotate2 = new RotateTransition();
        weaponRotate2.setNode(weapon);
        weaponRotate2.setByAngle(isPlayer1 ? 15 : -15);
        weaponRotate2.setDuration(Duration.seconds(1));

        TranslateTransition weaponMove2 = new TranslateTransition();
        weaponMove2.setNode(weapon);
        weaponMove2.setByX(isPlayer1 ? 10 : -10);
        weaponMove2.setByY(10);
        weaponMove2.setDuration(Duration.seconds(1));

        p2.getChildren().addAll(weaponRotate2, weaponMove2);

        ParallelTransition p3 = new ParallelTransition();

        RotateTransition weaponRotate3= new RotateTransition();
        weaponRotate3.setNode(weapon);
        weaponRotate3.setToAngle(0);
        weaponRotate3.setDuration(Duration.millis(500));

        TranslateTransition weaponMove3 = new TranslateTransition();
        weaponMove3.setNode(weapon);
        weaponMove3.setToX(0);
        weaponMove3.setToY(0);
        weaponMove3.setDuration(Duration.millis(500));

        p3.getChildren().addAll(weaponRotate3, weaponMove3);

        s1.getChildren().addAll(p1, p2, p3);

        parallelTransition.getChildren().addAll(s1);

        parallelTransition.play();
    }

    public void animateArmAttack() {
        ParallelTransition parallelTransition = new ParallelTransition();
        Duration duration = Duration.seconds(1);

        animateBody(parallelTransition, duration);

        //РУКА

        SequentialTransition s1 = new SequentialTransition();
        ParallelTransition p1 = new ParallelTransition();
        RotateTransition armRotate1 = new RotateTransition();
        armRotate1.setNode(arm);
        armRotate1.setByAngle(isPlayer1 ? 20 : -20);
        armRotate1.setDuration(Duration.millis(500));

        TranslateTransition armMove1 = new TranslateTransition();
        armMove1.setNode(arm);
        armMove1.setByX(isPlayer1 ? -30 : 30);
        armMove1.setByY(-20);
        armMove1.setDuration(Duration.millis(500));

        p1.getChildren().addAll(armMove1, armRotate1);

        ParallelTransition p2 = new ParallelTransition();

        RotateTransition armRotate2 = new RotateTransition();
        armRotate2.setNode(arm);
        armRotate2.setByAngle(isPlayer1 ? -40 : 40);
        armRotate2.setDuration(Duration.millis(500));

        TranslateTransition armMove2 = new TranslateTransition();
        armMove2.setNode(arm);
        armMove2.setByX(isPlayer1 ? 70 : -70);
        armMove2.setByY(30);
        armMove2.setDuration(Duration.millis(500));

        p2.getChildren().addAll(armRotate2, armMove2);

        ParallelTransition p3 = new ParallelTransition();

        RotateTransition armRotate3= new RotateTransition();
        armRotate3.setNode(arm);
        armRotate3.setToAngle(0);
        armRotate3.setDuration(Duration.seconds(1));

        TranslateTransition armMove3 = new TranslateTransition();
        armMove3.setNode(arm);
        armMove3.setToX(0);
        armMove3.setToY(0);
        armMove3.setDuration(Duration.seconds(1));

        p3.getChildren().addAll(armRotate3, armMove3);

        s1.getChildren().addAll(p1, p2, p3);

        parallelTransition.getChildren().addAll(s1);

        //ОРУЖИЕ
        RotateTransition weaponRotate = new RotateTransition();
        weaponRotate.setNode(weapon);
        weaponRotate.setByAngle(isPlayer1 ? 30 : -30);
        weaponRotate.setDuration(duration);
        weaponRotate.setAutoReverse(true);
        weaponRotate.setCycleCount(2);

        TranslateTransition weaponMove = new TranslateTransition();
        weaponMove.setNode(weapon);
        weaponMove.setByX(isPlayer1 ? 20 : -20);
        weaponMove.setByY(50);
        weaponMove.setDuration(duration);
        weaponMove.setAutoReverse(true);
        weaponMove.setCycleCount(2);

        parallelTransition.getChildren().add(weaponMove);
        parallelTransition.getChildren().add(weaponRotate);

        parallelTransition.play();
    }
}
