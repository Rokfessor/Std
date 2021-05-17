package controller;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Painter {
    public Circle stateMoveCircle;
    public Circle waitCircle;
    public Circle offOnCircle;
    public Circle symPresCond;
    public Circle symPresCondTrue;
    public Circle cCePresCond;
    public Circle cCePresCondTrue;
    public Circle cCePresCondFalse;
    public Circle actionCircle;
    public Circle doActCircle;
    public Circle printResCircle;
    public Circle invertCircle;
    public Circle memCircle;
    public Circle mrcCondFalse;
    public Circle mrcCond;
    public Circle mrcCondTrue;

    Duration duration = Duration.millis(500);
    long pause = 300L;


    public void drawOnOFF(boolean isOff) {
        TranslateTransition transition = new TranslateTransition(duration, stateMoveCircle);
        if (isOff) {
            transition.setToX(259 - stateMoveCircle.getCenterX());
            transition.setToY(58 - stateMoveCircle.getCenterY());
        } else {
            transition.setToX(309 - stateMoveCircle.getCenterX());
            transition.setToY(260 - stateMoveCircle.getCenterY());
        }
        transition.play();
        transition.setOnFinished((e) -> {
            if (isOff) {
                offOnCircle.setFill(Color.YELLOW);
                waitCircle.setFill(Color.WHITE);
            } else {
                waitCircle.setFill(Color.YELLOW);
                offOnCircle.setFill(Color.WHITE);
            }

        });
    }

    public void drawAddSymbol(boolean isCorrect) {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);
        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(124 - stateMoveCircle.getCenterX());
        tr1.setToY(173 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            waitCircle.setFill(Color.WHITE);
            symPresCond.setFill(Color.YELLOW);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        if (isCorrect) {
            tr2.setToX(195 - stateMoveCircle.getCenterX());
            tr2.setToY(100 - stateMoveCircle.getCenterY());

        } else {
            tr2.setToX(184 - stateMoveCircle.getCenterX());
            tr2.setToY(246 - stateMoveCircle.getCenterY());

        }
        tr2.setOnFinished((e) -> {
            symPresCond.setFill(Color.WHITE);
            if (isCorrect) {
                symPresCondTrue.setFill(Color.YELLOW);
            }
        });

        TranslateTransition tr3 = new TranslateTransition(duration, stateMoveCircle);
        tr3.setToX(309 - stateMoveCircle.getCenterX());
        tr3.setToY(260 - stateMoveCircle.getCenterY());

        tr3.setOnFinished((e) -> {
            if (isCorrect)
                symPresCondTrue.setFill(Color.WHITE);
            waitCircle.setFill(Color.YELLOW);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2, isCorrect ? new PauseTransition(Duration.millis(pause)) : new PauseTransition(Duration.millis(0)), tr3);
        transition.play();
    }

    public void drawClean(boolean isCE) {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);
        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(108 - stateMoveCircle.getCenterX());
        tr1.setToY(348 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            cCePresCond.setFill(Color.YELLOW);
            waitCircle.setFill(Color.WHITE);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        if (isCE) {
            tr2.setToX(101 - stateMoveCircle.getCenterX());
            tr2.setToY(257 - stateMoveCircle.getCenterY());
        } else {
            tr2.setToX(158 - stateMoveCircle.getCenterX());
            tr2.setToY(432 - stateMoveCircle.getCenterY());
        }

        tr2.setOnFinished((e) -> {
            cCePresCond.setFill(Color.WHITE);
            if (!isCE)
                cCePresCondTrue.setFill(Color.YELLOW);
            else
                cCePresCondFalse.setFill(Color.YELLOW);
        });

        TranslateTransition tr3 = new TranslateTransition(duration, stateMoveCircle);
        tr3.setToX(309 - stateMoveCircle.getCenterX());
        tr3.setToY(260 - stateMoveCircle.getCenterY());
        tr3.setOnFinished((e) -> {
            waitCircle.setFill(Color.YELLOW);
            if (!isCE)
                cCePresCondTrue.setFill(Color.WHITE);
            else
                cCePresCondFalse.setFill(Color.WHITE);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2, new PauseTransition(Duration.millis(pause)), tr3);
        transition.play();
    }

    public void drawAction() {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);

        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(237 - stateMoveCircle.getCenterX());
        tr1.setToY(487 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            waitCircle.setFill(Color.WHITE);
            actionCircle.setFill(Color.YELLOW);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        tr2.setToX(158 - stateMoveCircle.getCenterX());
        tr2.setToY(432 - stateMoveCircle.getCenterY());
        tr2.setOnFinished((e) -> {
            actionCircle.setFill(Color.WHITE);
            cCePresCondTrue.setFill(Color.YELLOW);
        });

        TranslateTransition tr3 = new TranslateTransition(duration, stateMoveCircle);
        tr3.setToX(309 - stateMoveCircle.getCenterX());
        tr3.setToY(260 - stateMoveCircle.getCenterY());
        tr3.setOnFinished((e) -> {
            cCePresCondTrue.setFill(Color.WHITE);
            waitCircle.setFill(Color.YELLOW);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2, new PauseTransition(Duration.millis(pause)), tr3);
        transition.play();
    }

    public void drawCalculate() {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);
        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(422 - stateMoveCircle.getCenterX());
        tr1.setToY(468 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            waitCircle.setFill(Color.WHITE);
            doActCircle.setFill(Color.YELLOW);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        tr2.setToX(501 - stateMoveCircle.getCenterX());
        tr2.setToY(411 - stateMoveCircle.getCenterY());
        tr2.setOnFinished((e) -> {
            doActCircle.setFill(Color.WHITE);
            printResCircle.setFill(Color.YELLOW);
        });

        TranslateTransition tr3 = new TranslateTransition(duration, stateMoveCircle);
        tr3.setToX(309 - stateMoveCircle.getCenterX());
        tr3.setToY(260 - stateMoveCircle.getCenterY());
        tr3.setOnFinished((e) -> {
            printResCircle.setFill(Color.WHITE);
            waitCircle.setFill(Color.YELLOW);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2, new PauseTransition(Duration.millis(pause)), tr3);
        transition.play();
    }

    public void drawInvert() {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);
        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(500 - stateMoveCircle.getCenterX());
        tr1.setToY(323 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            waitCircle.setFill(Color.WHITE);
            invertCircle.setFill(Color.YELLOW);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        tr2.setToX(309 - stateMoveCircle.getCenterX());
        tr2.setToY(260 - stateMoveCircle.getCenterY());
        tr2.setOnFinished((e) -> {
            invertCircle.setFill(Color.WHITE);
            waitCircle.setFill(Color.YELLOW);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2);
        transition.play();
    }

    public void drawMem() {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);
        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(505 - stateMoveCircle.getCenterX());
        tr1.setToY(242 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            waitCircle.setFill(Color.WHITE);
            memCircle.setFill(Color.YELLOW);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        tr2.setToX(309 - stateMoveCircle.getCenterX());
        tr2.setToY(260 - stateMoveCircle.getCenterY());
        tr2.setOnFinished((e) -> {
            memCircle.setFill(Color.WHITE);
            waitCircle.setFill(Color.YELLOW);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2);
        transition.play();
    }

    public void drawMRC(boolean isMC) {
        SequentialTransition transition = new SequentialTransition();
        transition.setNode(stateMoveCircle);
        TranslateTransition tr1 = new TranslateTransition(duration, stateMoveCircle);
        tr1.setToX(438 - stateMoveCircle.getCenterX());
        tr1.setToY(100 - stateMoveCircle.getCenterY());
        tr1.setOnFinished((e) -> {
            waitCircle.setFill(Color.WHITE);
            mrcCond.setFill(Color.YELLOW);
        });

        TranslateTransition tr2 = new TranslateTransition(duration, stateMoveCircle);
        if (isMC) {
            tr2.setToX(361 - stateMoveCircle.getCenterX());
            tr2.setToY(56 - stateMoveCircle.getCenterY());
        } else {
            tr2.setToX(500 - stateMoveCircle.getCenterX());
            tr2.setToY(160 - stateMoveCircle.getCenterY());
        }
        tr2.setOnFinished((e) -> {
            mrcCond.setFill(Color.WHITE);
            if (isMC)
                mrcCondTrue.setFill(Color.YELLOW);
            else
                mrcCondFalse.setFill(Color.YELLOW);
        });

        TranslateTransition tr3 = new TranslateTransition(duration, stateMoveCircle);
        tr3.setToX(309 - stateMoveCircle.getCenterX());
        tr3.setToY(260 - stateMoveCircle.getCenterY());
        tr3.setOnFinished((e) -> {
            waitCircle.setFill(Color.YELLOW);
            if (isMC)
                mrcCondTrue.setFill(Color.WHITE);
            else
                mrcCondFalse.setFill(Color.WHITE);
        });

        transition.getChildren().addAll(tr1, new PauseTransition(Duration.millis(pause)), tr2, new PauseTransition(Duration.millis(pause)), tr3);
        transition.play();
    }
}