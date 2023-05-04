package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.example.model.Car;
import org.example.model.CarPool;

public class AppController {

    @FXML
    Canvas canvas;

    @FXML
    Label winnerLabel;

    CarPool carPool;

    @FXML
    public void initialize() {
        carPool = new CarPool(canvas, integer -> { //Создание объекта для управления потоками
            Platform.runLater(() -> winnerLabel.setText("Winner is: " + integer));
            return 0;
        });
        Image carImage = new Image("Car.png");

        //Создание потоков
        carPool.addCar(new Car(carImage));
        carPool.addCar(new Car(carImage));
        carPool.addCar(new Car(carImage));
        carPool.addCar(new Car(carImage));
        carPool.addCar(new Car(carImage));

        carPool.reset();
    }


    //Обработчики нажатий на кнопки
    @FXML
    public void start() {
        carPool.start();
    }

    @FXML
    public void reset() {
        carPool.reset();
    }

    @FXML
    public void about() {
        AboutController aboutController = new AboutController(canvas.getScene().getWindow());
        aboutController.show();
    }

    @FXML
    public void exit() {
        carPool.reset();
        Platform.exit();
    }
}
