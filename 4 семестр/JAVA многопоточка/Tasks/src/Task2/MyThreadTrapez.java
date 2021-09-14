package Task2;

public class MyThreadTrapez extends Thread {
    double a, b;
    int n;
    public static double counter;

    static synchronized void addToCounter(double val) {
        counter += val;
    }

    static synchronized void resetCounter() {
        counter = 0L;
    }

    public MyThreadTrapez(double a, double b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Override
    public void run() {
        addToCounter(Calculator.calcTrapez(a, b, n));
    }
}
