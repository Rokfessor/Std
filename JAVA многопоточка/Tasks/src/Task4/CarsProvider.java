package Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarsProvider {
    private static int count;

    public static int provide(int capacity, long t, long h, int cars, int w) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity);
        long lamb = h / cars;
        count = 0;

        /*Thread th1 = new Thread(() -> {
            try {
                for (int i = 1; i < cars; i++) {
                    Thread.sleep(t);
                    String car = queue.poll();
                    if (car != null)
                        System.err.println("Машина " + car + " выехала с осмотра \n");
                    else {
                        System.err.println("Машин на осмотре больше нет");
                        break;
                    }
                }
            } catch (InterruptedException ignored) {
            }
        });*/

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < cars; i++) {
            String car = String.valueOf(i + 1);
            Thread thread = new Thread(() -> {
                System.err.println("Машина " + car + " едет на осмотр в \n");
                try {
                    long wa = System.currentTimeMillis();
                    boolean f = queue.offer(car, w, TimeUnit.MILLISECONDS);
                    wa = (System.currentTimeMillis() - wa) / 1000;
                    if (f) {
                        long finalWa = wa;
                        Thread th = new Thread(() -> {
                            System.err.println("Машина " + car + " подождала " + finalWa + " минут и заехала на осмотр \n");
                            try {
                                Thread.sleep(t);
                                if (queue.poll() != null)
                                    System.err.println("Машина " + car + " осмотрелась за " + (t / 1000) + " минуты и уехала\n");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
                        th.start();
                        th.join();
                        count++;
                    } else
                        System.err.println("Машина " + car + " подождала " + wa + " минут и НЕ заехала на осмотр \n");

                } catch (InterruptedException ignored) {}
            });
            threads.add(thread);
        }

        try {
            for (Thread th : threads) {
                th.start();
                Thread.sleep(lamb);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return count;
    }
}

