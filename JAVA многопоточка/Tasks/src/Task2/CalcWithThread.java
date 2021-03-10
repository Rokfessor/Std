package Task2;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;

public class CalcWithThread {
    public static double calcRect(double a, double b, int N, int n) throws InterruptedException {
        double step = (b - a) / ((double) N);
        int st = n / N;
        double start = a;
        List<MyThreadRect> threads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            double finalStart = start;
            MyThreadRect thread = new MyThreadRect(finalStart, finalStart + step, st);

            /*if (i != N - 1) {
                threads.add(new MyThreadRect(finalStart, finalStart + step, st));
            } else {
                threads.add(new MyThreadRect(finalStart, finalStart + step, n - (st * N)));
            }*/

            start += step;
            threads.add(thread);
        }

        for (MyThreadRect thread : threads)
            thread.start();

        for (MyThreadRect thread : threads)
            thread.join();

        double res = MyThreadRect.counter;
        MyThreadRect.resetCounter();

        return res;
    }

    public static double calcTrapez(double a, double b, int N, int n) throws InterruptedException {
        double step = (b - a) / ((double) N);
        int st = n / N;
        double start = a;
        List<MyThreadTrapez> threads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            double finalStart = start;
            MyThreadTrapez thread = new MyThreadTrapez(finalStart, finalStart + step, st);
            start += step;
            threads.add(thread);
        }

        for (MyThreadTrapez thread : threads)
            thread.start();

        for (MyThreadTrapez thread : threads)
            thread.join();

        double res = MyThreadTrapez.counter;
        MyThreadTrapez.resetCounter();

        return res;
    }

    public static double calcSimps(double a, double b, int N, int n) throws InterruptedException {
        double step = (b - a) / ((double) N);
        int st = n / N;
        double start = a;
        List<MyThreadSimps> threads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            double finalStart = start;
            MyThreadSimps thread = new MyThreadSimps(finalStart, finalStart + step, st);
            start += step;
            threads.add(thread);
        }

        for (MyThreadSimps thread : threads)
            thread.start();

        for (MyThreadSimps thread : threads)
            thread.join();

        double res = MyThreadSimps.counter;
        MyThreadSimps.resetCounter();

        return res;
    }
}
