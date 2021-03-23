import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Calculator {
    static int[] KRATN = {1, 1, 1, 1, 1};
    static int[] SOL = new int[KRATN.length];
    public static List<String> res = new ArrayList<>();

    public static void calc(int V, int index) {
        for (int i = index; i >= 0; --i) {
            if ((KRATN[i] > 0) && (i + 1 <= V)) {
                KRATN[i]--;
                SOL[i]++;
                System.err.println(Arrays.toString(SOL));
                V -= (i + 1);
                System.err.println(V);
                if (V == 0) {
                    System.err.println("!");
                    res.add(Arrays.toString(SOL));
                } else {
                    calc(V, i);
                }


                KRATN[i]++;
                SOL[i]--;
                V += (i + 1);

            }
        }
    }
    /*public static void calc(int weight, int[] mass) {
        for (int i = 1; i < (int) Math.pow(2, mass.length) - 1; i++) {
            char[] m = Integer.toBinaryString(i).toCharArray();
            int sum = 0;
            for (int j = 0; j < m.length; j++) {
                if (m[j] == '1')
                    sum += mass[j];
            }


            System.err.println(Arrays.toString(mass) + " = " + Integer.toBinaryString(i) + " | " + sum);
        }
    }*/
}
