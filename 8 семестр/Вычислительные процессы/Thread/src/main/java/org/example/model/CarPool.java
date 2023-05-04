package org.example.model;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CarPool {
    private final Canvas canvas;
    private final List<Car> cars;
    private final GraphicsContext context;

    private final int START_X = 200;
    private final int FINISH_X = 700;
    private final int OBJ_WIDTH = 100;

    private final Function<Integer, ?> onWin;

    public CarPool(Canvas canvas, Function<Integer, ?> onWin) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.onWin = onWin;
        this.cars = new ArrayList<>();
        redraw();
    }

    //Добавление потока в список
    public void addCar(Car car) {
        car.setCarNumber(cars.size());
        car.setPool(this);
        cars.add(car);
    }

    //Обновление позиции потоков на экране
    public void updateCarPosition(int num, int xPosition) {
        if (cars.get(num).xPosition >= FINISH_X - OBJ_WIDTH) { //Проверка на победу
            onWin.apply(num); //вызов функции для обработки победы
            stop(); //Остановка заезда
            return;
        }

        cars.get(num).xPosition += xPosition; //Обновление позиции
        Platform.runLater(() -> redraw()); // Перерисовка
    }

    //Метод перерисовки
    private void redraw() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //Очистка экрана
        drawField();
        for (int i = 0; i < cars.size(); ++i) { //Отрисовка всех элементов в списке
            context.setStroke(Color.grayRgb(i * 50));
            context.strokeOval(cars.get(i).xPosition, (i * 110) + 20, OBJ_WIDTH, OBJ_WIDTH);
            context.strokeText(String.valueOf(i), cars.get(i).xPosition + OBJ_WIDTH / 2., (i * 110) + 20 + OBJ_WIDTH / 2.);
        }
    }

    //Отрисовка поля
    private void drawField() {
        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.strokeText("Start", OBJ_WIDTH, 10);
        context.strokeText("Finish", FINISH_X, 10);
        context.setLineWidth(3);
        context.strokeLine(OBJ_WIDTH, 20, OBJ_WIDTH, 580);
        context.strokeLine(FINISH_X, 20, FINISH_X, 580);
    }

    //Сброс заезда
    public void reset() {
        stop();

        for (Car car : cars)
            car.xPosition = 0;

        redraw();
    }

    //Остановка заезда
    public void stop() {
        for (Car car : cars)
            car.pause();
        redraw();
    }

    //Начало заезда
    public void start() {
        for (Car car : cars)
            car.ride();
    }
}
