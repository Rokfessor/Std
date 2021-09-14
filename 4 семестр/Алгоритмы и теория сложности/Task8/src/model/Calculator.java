package model;

import java.util.Arrays;

public class Calculator {
    public static boolean[][] calculate(int[] kett, int maxWeight) {
        int N = kett.length;
        boolean[][] res = new boolean[maxWeight + 1][N + 1];
        String[][] t = new String[maxWeight + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            res[0][i] = true;
            t[0][i] = i + " ";
        }

        for (int i = 1; i <= maxWeight; i++) {
            res[i][0] = false;
            t[i][0] = "";
        }

        for (int i = 1; i <= maxWeight; i++) {
            for (int j = 1; j <= N; j++) {
                res[i][j] = res[i][j - 1];
                t[i][j] = i + " ";
                System.err.println("i=" + i + " j=" + j + " res[i][j]=" + res[i][j] + " kett[j-1]=" + kett[j - 1]);
                if (i >= kett[j - 1]) {
                    res[i][j] = res[i][j] || res[i - kett[j - 1]][j - 1];
                    System.err.println(i + ">=" + kett[j - 1] + "  res[i][j]=" + res[i][j] + "  res[i - kett[j-1]] [j-1]=" + (res[i - kett[j - 1]][j - 1]) + "  " + (i - kett[j - 1]) + " " + (j - 1));
                    t[i][j] = (j - 1) + " ";
                }
            }
        }
        System.err.println(Arrays.deepToString(t));
        return Arrays.copyOfRange(res, 1, res.length);
    }
}
