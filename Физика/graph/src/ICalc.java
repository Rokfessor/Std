

public class ICalc {
    static double calc(double a, double d, double N, double λ, double x){
        return (Math.pow((Math.sin(Math.PI * a * Math.sin(x))/λ),2)/Math.pow(((Math.PI * a * Math.sin(x))/λ),2))*
                (Math.pow((Math.sin(N * Math.PI * d * Math.sin(x))/λ),2)/Math.pow(((N * Math.PI * d * Math.sin(x))/λ),2));
    }
}
