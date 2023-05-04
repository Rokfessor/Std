package org.example.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class ThreadWatcher extends Thread{

    private final int maxPosition;
    private List<Car> cars;
    private Function<Integer,?> function;

    public ThreadWatcher(List<Car> cars, int maxPosition, Function<Integer,?> function) {
        this.cars = cars;
        this.maxPosition = maxPosition;
        this.function = function;
    }

    private final AtomicBoolean running = new AtomicBoolean(true);

    @Override
    public void interrupt() {
        running.set(false);
        super.interrupt();
    }

    @Override
    public void run() {
        while (running.get()) {
            int i = 0;
            for (Car car : cars) {
                if (car.xPosition > maxPosition) {
                    function.apply(i);
                }
                ++i;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {}
        }
    }
}
