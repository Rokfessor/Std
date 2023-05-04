package org.example.model;

import javafx.scene.image.Image;

import java.util.concurrent.atomic.AtomicBoolean;

//Класс, представляющий поток
public class Car extends Thread {
    private int carNumber;
    private CarPool carPool;
    public final Image image;
    public int xPosition;

    private AtomicBoolean running = new AtomicBoolean(false);

    public Car(Image image) {
        this.image = image;
        xPosition = 0;
        start();
    }

    //Установка управляющего отрисовкой на экране класса
    public void setPool(CarPool carPool) {
        this.carPool = carPool;
    }

    //Установка номера машины для обновления в списке
    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    //Остоновка потока
    public void pause() {
        running.set(false);
    }

    //Запуск потока
    public void ride() {
        running.set(true);
    }

    @Override
    public void run() {
        while (true) {
            while (running.get()) { //Приостановка потока
                try {
                    int t = (int) (Math.random() * 100); //Генерируем время работы
                    long startTime = System.currentTimeMillis(); //Получаем время начала работы
                    while (System.currentTimeMillis() - startTime < t) { //Пока время текущей работы < сгенерированной
                        carPool.updateCarPosition(carNumber, 1); //Обновляем свою позицию
                        Thread.sleep(1L); //Засыпаем, чтобы не было слишком много обновлений позиции
                    }

                    Thread.sleep((long) (Math.random() * 3000)); //Засыпаем на случайное время
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
