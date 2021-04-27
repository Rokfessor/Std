import java.util.Arrays;

public class Calculator {
    private static int VES;
    private static int N = 3;
    private static int optCost;
    private static Obj[] A;
    private static boolean[] X, OptX;
    private static DataHolder dataHolder;
    private static String[] usedObj;
    private static long step;

    private static void myTry(int i, int sum_v, int pos_st) {
        if (sum_v + A[i].v <= VES) {
            X[i] = true;
            step++;
            usedObj[i] = "+";
            if ((i == (N - 1)) && (pos_st > optCost)) {
                OptX = Arrays.copyOf(X, X.length);
                optCost = pos_st;
                step += 3;
                writeStep(i, optCost, pos_st, "Оптимум");
            } else {
                step++;
                writeStep(i, optCost, pos_st, "Вкл." + (i + 1));
                myTry(i + 1, sum_v + A[i].v, pos_st);
            }
            usedObj[i] = "?";
            X[i] = false;
            step++;
        }
        int st1 = pos_st - A[i].st;
        step++;
        usedObj[i] = "-";
        if (st1 > optCost) {
            step++;
            if (i == (N - 1)) {
                OptX = Arrays.copyOf(X, X.length);
                optCost = st1;
                step += 3;
                writeStep(i, optCost, st1, "Оптимум");
            } else {
                step++;
                writeStep(i, optCost, st1, st1 + ">" + optCost + " Невкл." + (i + 1));
                myTry(i + 1, sum_v, st1);
            }
        } else {
            if (i != (N - 1))
                writeStep(i, optCost, st1, st1 + "<=" + optCost + " Невкл." + (i + 1) + " неприем");
            else {
                writeStep(i, optCost, st1, st1 + "<" + optCost + " Невкл." + (i + 1) + " неприем");
            }
        }
        usedObj[i] = "?";
    }

    private static void writeStep(int i, int optCost, int pos_st, String note) {
        String tryI = "try(" + (i + 1) + ")";
        dataHolder.steps.add(new Step(tryI, Arrays.copyOf(usedObj, usedObj.length), sumV(), sumCost(), pos_st, optCost, note));
    }

    private static int sumV() {
        int res = 0;
        for (int i = 0; i < X.length; i++) {
            if (X[i])
                res += A[i].v;
        }

        return res;
    }

    private static String[] convertX() {
        String[] s = new String[X.length];
        for (int i = 0; i < s.length; i++) {
            if (X[i])
                s[i] = "+";
            else
                s[i] = "-";
        }
        return s;
    }

    public static int sumCost() {
        int sumCost = 0;
        for (int i = 0; i < N; i++) {
            if (X[i])
                sumCost += A[i].st;
        }
        return sumCost;
    }

    public static DataHolder calculate(int VES, Obj[] mass) {
        dataHolder = new DataHolder();
        Calculator.VES = VES;
        int sumCost = 0;
        A = mass;
        N = mass.length;
        X = new boolean[N];
        OptX = new boolean[N];
        usedObj = new String[N];
        for (int i = 0; i < N; i++) {
            sumCost += A[i].st;
            X[i] = false;
            OptX[i] = false;
            usedObj[i] = "?";
        }
        optCost = 0;
        step = 0;
        myTry(0, 0, sumCost);

        Obj[] res = new Obj[0];
        int len = 1;
        for (int i = 0; i < OptX.length; i++) {
            if (OptX[i]) {
                res = Arrays.copyOf(res, len);
                res[len - 1] = A[i];
                len++;
            }
        }
        dataHolder.result = res;
        dataHolder.step = step;

        return dataHolder;
    }
}