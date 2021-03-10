package Task1;

import java.util.ArrayList;
import java.util.List;

public class Find {
    public static Counter findWithThread(int size, int threads) throws InterruptedException {
        Counter counter = new Counter();
        long endRange = (long) Math.pow(10.0, size) - 1L;
        long startRange = (long) (Math.pow(10.0, size - 1));
        long step = (endRange - startRange) / threads;
        long prevStepStop = startRange;
        List<MyThread> myThreads = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            MyThread myThread;
            if (i != threads - 1) {
                myThread = new MyThread(prevStepStop, prevStepStop + step, counter);
                prevStepStop += step;
            } else {
                myThread = new MyThread(prevStepStop, endRange, counter);
            }
            myThreads.add(myThread);
        }

        for (MyThread thread : myThreads) {
            thread.start();
        }

        for (MyThread thread : myThreads) {
            thread.join();
        }

        return counter;
    }

    public static Counter findWithRunnable(int size, int threads) throws InterruptedException {
        Counter counter = new Counter();
        long endRange = (long) Math.pow(10.0, size) - 1L;
        long startRange = (long) (Math.pow(10.0, size - 1));
        long step = (endRange - startRange) / threads;
        long prevStepStop = startRange;
        List<Thread> myThreads = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            Thread thread;
            if (i != threads - 1) {
                thread = new Thread(new MyRunnable(prevStepStop, prevStepStop + step, counter));
                prevStepStop += step;
            } else {
                thread = new Thread(new MyRunnable(prevStepStop, endRange, counter));
            }
            myThreads.add(thread);
        }

        for (Thread thread : myThreads) {
            thread.start();
        }

        for (Thread thread : myThreads) {
            thread.join();
        }

        return counter;
    }
}

