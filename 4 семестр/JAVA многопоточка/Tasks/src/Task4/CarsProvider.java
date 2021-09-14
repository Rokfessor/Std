package Task4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarsProvider extends Thread {
    private static int carsCount;
    private static int obslCarsCount;
    public static boolean stop = false;
    private static List<Thread> threads = new ArrayList<>();
    private Owner owner;
    private final long mills = 60000;

    public CarsProvider(Owner owner) {
        this.owner = owner;
    }

    public synchronized void provide(int capacity, long t, long w, long newCar, double otnW) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity);
        carsCount = 0;
        obslCarsCount = 0;
        try {
            long tStart = System.currentTimeMillis();
            System.err.println("==" + new SimpleDateFormat("hh:mm:ss").format(0) + "== " + tStart);

            while (!stop) {
                Thread thread = new Thread(() -> {
                    String car = String.valueOf(carsCount);
                    long temp = System.currentTimeMillis();
                    System.err.println(new SimpleDateFormat("hh:mm:ss").format(((temp + ((temp - tStart) * otnW)) - tStart))
                            + " " + "Машина " + car + " едет на осмотр");
                    try {
                        long wRand = w == 0 ? 0 : (long) new Random().nextGaussian() + w ;
                        long wa = System.currentTimeMillis();
                        boolean f = queue.offer(car, (long) ((wRand / otnW) * mills), TimeUnit.MILLISECONDS);
                        wa = (long) ((System.currentTimeMillis() - wa) * otnW) / 60000;

                        if (f) {
                            long finalWa = wa;
                            Thread th = new Thread(() -> {
                                long temp1 = System.currentTimeMillis();

                                System.out.println(new SimpleDateFormat("hh:mm:ss").format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                        + " " + "Машина " + car + " подождала " + finalWa + " мин. и заехала на осмотр");

                                try {

                                    //(long)(t + (Math.random()) * (long)(t * 0.2))
                                    long tRand = (long) Math.abs(new Random().nextGaussian() * t + (t / 2));
                                    Thread.sleep((long) ((tRand / otnW) * mills));

                                    if (queue.poll() != null) {

                                        temp1 = System.currentTimeMillis();
                                        System.out.println(new SimpleDateFormat("hh:mm:ss").format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                                + " " + "Машина " + car + " осмотрелась за " + tRand + " мин. и уехала");
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            });
                            th.start();
                            th.join();
                            obslCarsCount++;
                        } else {
                            long temp1 = System.currentTimeMillis();
                            System.err.println(new SimpleDateFormat("hh:mm:ss").format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                    + " " + "Машина " + car + " подождала " + wa + " мин. и НЕ заехала на осмотр");
                        }
                    } catch (InterruptedException ignored) {
                    }
                });

                thread.start();
                threads.add(thread);
                ++carsCount;
                long randNewCar = (long) Math.abs(new Random().nextGaussian() * newCar + (newCar / 2)) + 1;
                owner.carsProvider.wait((long) ((randNewCar / otnW) * mills));
            }
            long tEnd = System.currentTimeMillis();
            System.err.println("==" + new SimpleDateFormat("hh:mm:ss").format(tEnd + ((tEnd - tStart) * otnW) - tStart) + "== " + (tEnd));
            for (Thread thread : threads) {
                thread.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double otn = (double) obslCarsCount / carsCount;
        System.err.println("Всего машин = " + carsCount + "\nОбслуженные машины = " + obslCarsCount);
        System.err.println("Относительное = " + (int)(otn * 100) + "%");
        System.err.println("Абсолютное = " + otn);
    }
}

