package Task5;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CarsProvider extends Thread {
    private static int clientsCount;
    private static int obslCarsCount;
    public static boolean stop = false;
    private static List<Thread> threads = new ArrayList<>();
    private Owner owner;
    private final long mills = 60000;

    public CarsProvider(Owner owner) {
        this.owner = owner;
    }

    public synchronized void provide(int capacity, long t, double w, double newClient, double otnW) {
        Semaphore semaphore = new Semaphore(capacity);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        NumberFormat numberFormat = new DecimalFormat("#0.00");
        clientsCount = 0;
        obslCarsCount = 0;
        try {
            long tStart = System.currentTimeMillis();
            System.err.println("==" + dateFormat.format(0) + "== " + tStart);

            while (!stop) {
                Thread thread = new Thread(() -> {
                    String client = String.valueOf(clientsCount);
                    long temp = System.currentTimeMillis();
                    System.err.println(dateFormat.format(((temp + ((temp - tStart) * otnW)) - tStart))
                            + " Клиент " + client + " начинает звонок");
                    try {
                        double wRand = w == 0 ? 0 :  Math.abs((new Random().nextGaussian() * 0.05) + w);
                        double wa = (double) System.currentTimeMillis();

                        boolean f = semaphore.tryAcquire((long) ((wRand / otnW) * mills), TimeUnit.MILLISECONDS);
                        wa = ((System.currentTimeMillis() - wa) * otnW) / 60000F;
                        if (f) {
                            double finalWa = wa;
                            Thread th = new Thread(() -> {
                                long temp1 = System.currentTimeMillis();

                                System.out.println(dateFormat.format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                        +" Клиент " + client + " подождал " + numberFormat.format(finalWa) + " мин. и начал разговор с оператором");

                                try {

                                    //(long)(t + (Math.random()) * (long)(t * 0.2))
                                    double tRand = Math.abs((new Random().nextGaussian() * 1) + (t));
                                    Thread.sleep((long) ((tRand / otnW) * mills));
                                    semaphore.release();
                                    temp1 = System.currentTimeMillis();
                                    System.out.println(dateFormat.format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                            + " Клиент " + client + " поговорил за " + numberFormat.format(tRand) + " мин. и положил трубку");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                            th.start();
                            th.join();
                            obslCarsCount++;
                        } else {
                            long temp1 = System.currentTimeMillis();
                            System.err.println(dateFormat.format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                    + " " + "Клиент " + client + " подождал " + numberFormat.format(wa) + " мин. и НЕ начал разговор с оператором");
                        }
                    } catch (InterruptedException ignored) {}
                });

                thread.start();
                threads.add(thread);
                ++clientsCount;
                double randNewClient = Math.abs((new Random().nextGaussian() * 1) + (newClient));
                owner.carsProvider.wait((long) ((randNewClient / otnW) * mills));
            }
            long tEnd = System.currentTimeMillis();
            System.err.println("==" + new SimpleDateFormat("hh:mm:ss").format(tEnd + ((tEnd - tStart) * otnW) - tStart) + "== " + (tEnd));
            for (Thread thread : threads) {
                thread.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double otn = (double) obslCarsCount / clientsCount;
        System.err.println("Всего машин = " + clientsCount + "\nОбслуженные машины = " + obslCarsCount);
        System.err.println("Относительное = " + (int)(otn * 100) + "%");
        System.err.println("Абсолютное = " + otn);
    }
}

