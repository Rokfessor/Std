package Task2;

public class Calculator {
    public static double calcRect(double a, double b, int n) {
        double h = (b - a) / ((double) n);
        double res = 0;
        double x = a + (h / 2.);
        for (int i = 0; i < n; i++) {
            res += calcFunc(x + (h / 2));
            x += h;
        }

        //Counter.Coun.res += res * h;
        return res * h;
    }

    public static double calcTrapez(double a, double b, int n) {
        double h = (b - a) / ((double) n);
        double res = (calcFunc(a) + calcFunc(b)) / 2;
        double x = a + h;
        for (int i = 1; i < n - 2; i++) {
            res += calcFunc(x);
            x += h;
        }

        return res * h;
    }

    public static double calcSimps(double a, double b, int n) {
        if (n % 2 != 0)
            n--;

        double h = (b - a) / (n);
        double res = calcFunc(a) + calcFunc(b);
        a += h;

        for (int i = 1; i < n; i++) {
            if (i % 2 == 0)
                res += 4 * calcFunc(a);
            else
                res += 2 * calcFunc(a);
            a += h;
        }

        return res * (h / 3.);
    }

    private static double calcFunc(double x) {
        return Math.pow(Math.tan(x), 2) + Math.pow(1. / Math.tan(x), 2);
    }
}
