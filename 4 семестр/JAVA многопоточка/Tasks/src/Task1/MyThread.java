package Task1;

public class MyThread extends Thread {
    long start, end;
    Counter counter;

    MyThread(long start, long end, Counter counter) {
        this.start = start;
        this.end = end;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (long i = start; i < end; i++) {
            int n1 = (int) (i % 10), n2;
            long t = i / 10;
            boolean flag = true;
            while (t > 0) {
                n2 = n1;
                n1 = (int) (t % 10);
                t = t / 10;
                if (Math.abs(n1 - n2) != 1 || n1 == 0 || n2 == 0)
                    flag = false;
            }
            if (flag) {
                if (i % 2 == 0)
                    Counter.Coun.even++;
                else
                    Counter.Coun.odd++;
            }
        }
    }
}
