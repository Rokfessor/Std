package model;

import java.util.Arrays;
import java.util.List;

public class Calculator {
    public static int[][] calculate(List<Obj> objects, int maxWeight) {
        int N = objects.size();

        int[][] mass = new int[N + 1][maxWeight + 1];

        for (int i = 0; i <= maxWeight; i++) {
            mass[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (objects.get(i - 1).getWeight() > j) {
                    mass[i][j] = mass[i - 1][j];
                } else {
                    mass[i][j] = Math.max(mass[i - 1][j], mass[i - 1][j - objects.get(i - 1).getWeight()] + objects.get(i - 1).getCost());
                }
            }
        }

        int optCost = mass[N][maxWeight];

        return Arrays.copyOfRange(mass, 1, mass.length);
    }
}
