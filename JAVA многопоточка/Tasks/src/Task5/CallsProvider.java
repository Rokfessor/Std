package Task5;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CallsProvider extends Thread {
    private static int clientsCount;
    private static int obslCarsCount;
    public static boolean stop = false;
    private static List<Thread> threads = new ArrayList<>();
    private Owner owner;
    private final long mills = 60000;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    private static NumberFormat numberFormat = new DecimalFormat("#0.00");
    public CallsProvider(Owner owner) {
        this.owner = owner;
    }

    public synchronized void provide(int capacity, long t, double w, double newClient, double otnW) {
        Semaphore semaphore = new Semaphore(capacity);

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
                        double wRand = rand(0.05 , w, otnW);//w == 0 ? 0 :  Math.abs((new Random().nextGaussian() * 0.05) + w);
                        double wa = (double) System.currentTimeMillis();

                        boolean f = semaphore.tryAcquire((long) ((wRand / otnW) * mills), TimeUnit.MILLISECONDS);
                        wa = ((System.currentTimeMillis() - wa) * otnW) / 60000F;
                        if (f) {
                            double finalWa = wa;
                            Thread th = new Thread(() -> {
                                long temp1 = System.currentTimeMillis();

                                System.out.println(dateFormat.format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                        +" Клиент " + client + " подождал " + ((int)(finalWa * 60)) + " сек. и начал разговор с оператором");

                                try {
                                    //(long)(t + (Math.random()) * (long)(t * 0.2))
                                    double tRand = rand(1, t, otnW);//Math.abs((new Random().nextGaussian() * 1) + (t));
                                    Thread.sleep((long) ((tRand / otnW) * mills));
                                    semaphore.release();
                                    temp1 = System.currentTimeMillis();
                                    System.out.println(dateFormat.format(((temp1 + ((temp1 - tStart) * otnW)) - tStart))
                                            + " Клиент " + client + " поговорил за " + ((int)(tRand * 60)) + " сек. и положил трубку");
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
                                    + " " + "Клиент " + client + " подождал " + ((int)(wa * 60)) + " сек. и НЕ начал разговор с оператором");
                        }
                    } catch (InterruptedException ignored) {}
                });

                thread.start();
                threads.add(thread);
                ++clientsCount;
                double randNewClient = rand(1, newClient, otnW);//Math.abs((new Random().nextGaussian() * 1) + (newClient));
                owner.callsProvider.wait((long) ((randNewClient / otnW) * mills));
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
        System.err.println("Всего клиентов = " + clientsCount + "\nОбслуженные клиенты = " + obslCarsCount);
        System.err.println("Относительное = " + (int)(otn * 100) + "%");
        System.err.println("Абсолютное = " + otn);
    }

    private static String perform(String i){
        String a = i.split(",")[0];
        String b = String.valueOf(Integer.parseInt(i.split(",")[0]) * 60F);
        return a + "," + b;
    }

    private static double rand(double averange, double deviation, double coeff){
        double res = Math.abs((new Random().nextGaussian() * deviation) + averange);
        while ((long) (res * 6000/coeff) < 1) res = rand(averange, deviation, coeff);
        return res;
    }
}

