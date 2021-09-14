package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    private static Domino[] F;
    private static int[] CurPos;
    private static int BestPosN;
    private static int NF;
    private static List<Domino[]> res;
    private static CalcListener listener;
    public static long sleep = 1000;

    public static void setCalcListener(CalcListener listener) {
        Calculator.listener = listener;
    }

    public static void calculate(Domino[] mass) {
        BestPosN = 0;
        F = Arrays.copyOf(mass, mass.length);
        NF = F.length;
        res = new ArrayList<>();

        for (int i = 0; i < NF; i++) {
            CurPos = new int[0];
            put(true, i);
            F[i].used = true;
            Step(1, F[i].left, F[i].right);
            pull(true);
            F[i].used = false;
        }
    }

    private static void Step(int curn, int left, int right) {
        if (curn == BestPosN) {
            Domino[] t = new Domino[CurPos.length];
            for (int i = 0; i < CurPos.length; i++) {
                t[i] = F[CurPos[i]];
            }
            boolean flag = true;
            for (Domino[] m : res) {
                if (Arrays.equals(m, t)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                res.add(Arrays.copyOf(t, t.length));

            printRes();
        } else if (curn > BestPosN) {
            BestPosN = curn;
            res = new ArrayList<>();
            Domino[] t = new Domino[CurPos.length];
            for (int i = 0; i < CurPos.length; i++) {
                t[i] = F[CurPos[i]];
            }
            res.add(Arrays.copyOf(t, t.length));
            printRes();
        }

        for (int i = 0; i < NF; i++) {
            if (!F[i].used) {
                F[i].used = true;
                if (F[i].left == left) {
                    put(true, i);
                    F[i].inverted = true;
                    printCurPos();
                    Step(curn + 1, F[i].right, right);
                    F[i].inverted = false;
                    pull(true);
                }
                if (F[i].left == right) {
                    put(false, i);
                    printCurPos();
                    Step(curn + 1, left, F[i].right);
                    pull(false);
                }
                if (F[i].right == left) {
                    put(true, i);
                    printCurPos();
                    Step(curn + 1, F[i].left, right);
                    pull(true);
                }
                if (F[i].right == right) {
                    put(false, i);
                    F[i].inverted = true;
                    printCurPos();
                    Step(curn + 1, left, F[i].left);
                    F[i].inverted = false;
                    pull(false);
                }
                F[i].used = false;
            }
        }
    }

    private static void put(boolean left, int val) {
        if (left) {
            int[] temp = Arrays.copyOf(CurPos, CurPos.length);
            CurPos = new int[CurPos.length + 1];
            CurPos[0] = val;
            for (int i = 0; i < temp.length; i++) {
                CurPos[i + 1] = temp[i];
            }
        } else {
            CurPos = Arrays.copyOf(CurPos, CurPos.length + 1);
            CurPos[CurPos.length - 1] = val;
        }
    }

    private static void pull(boolean left) {
        if (left) {
            int[] temp = Arrays.copyOf(CurPos, CurPos.length);
            CurPos = new int[CurPos.length - 1];
            for (int i = 1; i < temp.length; i++) {
                CurPos[i - 1] = temp[i];
            }
        } else {
            if (CurPos.length - 1 != -1)
                CurPos = Arrays.copyOf(CurPos, CurPos.length - 1);
            else
                CurPos = new int[0];
        }
    }

    private static void printRes() {
        listener.setResult(res);
    }

    private static void printCurPos() {
        Domino[] dominos = new Domino[CurPos.length];
        for (int i = 0; i < CurPos.length; i++) {
            dominos[i] = F[CurPos[i]];
        }
        listener.stateChanged(dominos);

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
