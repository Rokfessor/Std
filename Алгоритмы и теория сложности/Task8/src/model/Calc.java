package model;

import java.util.ArrayList;
import java.util.List;

public class Calc {
    private static int[] KRATN;
    private static int[] SOL;
    private static List<String> res;
    private static long sleep = 1000;
    private static int V;

    public static String calculate(int V, int[] mass) throws InterruptedException {
        KRATN = mass;
        Calc.V = V;
        res = new ArrayList<>();
        SOL = new int[KRATN.length];
        calc(V, KRATN.length - 1);
        return formatResult();
    }

    private static String formatResult() {
        StringBuilder sb = new StringBuilder();
        for (String s : res) {
            for (int i = 0; i < s.length(); i++) {
                int a = Integer.parseInt(String.valueOf(s.charAt(i)));
                if (a != 0) {
                    for (int j = 0; j < a; j++) {
                        sb.append((i + 1)).append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
        }
        return sb.toString();
    }

    private static void calc(int V, int index) throws InterruptedException {
        for (int i = index; i >= 0; --i) {
            if ((KRATN[i] > 0) && (i + 1 <= V)) {
                KRATN[i]--;
                SOL[i]++;
                V -= (i + 1);
                if (V == 0) {
                    res.add(arrToString(SOL));
                } else {
                    calc(V, i);
                }
                KRATN[i]++;
                SOL[i]--;
                V += (i + 1);
            }
        }
    }
}
