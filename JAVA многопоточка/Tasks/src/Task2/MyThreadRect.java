package Task2;

public class MyThreadRect extends Thread {
    double a, b;
    int n;
    public static double counter = 0;

    static synchronized void addToCounter(double val) {
        counter += val;
    }

    static synchronized void resetCounter() {
        counter = 0L;
    }

    public MyThreadRect(double a, double b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Override
    public void run() {
        addToCounter(Calculator.calcRect(a, b, n));
    }
}
