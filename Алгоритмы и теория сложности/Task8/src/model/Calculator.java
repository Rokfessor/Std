package model;

import java.util.Arrays;

public class Calculator {
    public static boolean[][] calculate(int[] kett, int maxWeight) {
        int N = kett.length;
        boolean[][] res = new boolean[maxWeight + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            res[0][i] = true;
        }

        for (int i = 1; i <= maxWeight; i++) {
            res[i][0] = false;
        }

        for (int i = 1; i <= maxWeight; i++) {
            for (int j = 1; j <= N; j++) {
                res[i][j] = res[i][j - 1];
                if (i >= kett[j - 1]) {
                    res[i][j] = res[i][j] || res[i - kett[j - 1]][j - 1];
                }
            }
        }

        return Arrays.copyOfRange(res, 1, res.length);
    }
}
