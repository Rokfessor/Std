import java.util.Arrays;

public class ApproxCalculator {
    public static ApproxDataHolder calculate(int v, Obj[] mass) {
        double[] c = new double[mass.length];
        int step = 0;
        for (int i = 0; i < mass.length; i++) {
            c[i] = (double) mass[i].st / mass[i].v;
            step++;
        }
        Arrays.sort(c);
        int tv = 0;
        Obj[] res = new Obj[0];
        int a= 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i].v + tv <= v){
                tv+=mass[i].v;
                res = Arrays.copyOf(res, res.length+1);
                res[a] = mass[i];
                a++;
                step+=2;
            }
            step++;
        }
        return new ApproxDataHolder(mass, res, step);
    }
}
