package Task1;

public class CompoundCalc {
    public static double calcFV(double PV, double i, double n) {
        return PV * Math.pow(1. + i, n);
    }

    public static double calcPV(double FV, double i, double n) {
        return FV / Math.pow(1. + i, n);
    }

    public static double calcI(double FV, double PV, double n) {
        return Math.pow(FV / PV, 1. / n) - 1.;
    }

    public static double calcN(double FV, double PV, double i) {
        return Math.log(FV) / Math.log(PV * (1. + i));
    }
}
