package Task1;

public class SimpleCalc {
    public static double calcFV(double PV, double i, double n) {
        return PV * (1 + i * n);
    }

    public static double calcPV(double FV, double i, double n) {
        return FV / (1 + i * n);
    }

    public static double calcI(double FV, double PV, double n) {
        return ((FV / PV) - 1) / n;
    }

    public static double calcN(double FV, double PV, double i) {
        return ((FV / PV) - 1) / i;
    }
}
