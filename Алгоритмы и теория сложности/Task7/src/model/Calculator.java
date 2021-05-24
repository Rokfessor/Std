package model;

import java.util.Arrays;
import java.util.List;

public class Calculator {
    public static Holder calculate(List<Obj> objects, int maxWeight) {
        int N = objects.size();

        int[][] mass = new int[N + 1][maxWeight + 1];

        String[][] list = new String[N + 1][maxWeight + 1];

        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < maxWeight + 1; j++) {
                list[i][j] = "";
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            list[0][i + 1] = objects.get(i).getName();
        }

        for (int i = 0; i <= maxWeight; i++) {
            mass[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (objects.get(i - 1).getWeight() > j) {
                    mass[i][j] = mass[i - 1][j];
                    list[i][j] = list[i - 1][j];
                } else {
                    mass[i][j] = Math.max(mass[i - 1][j], mass[i - 1][j - objects.get(i - 1).getWeight()] + objects.get(i - 1).getCost());
                    int c1 = mass[i - 1][j];
                    int c2 = mass[i - 1][j - objects.get(i - 1).getWeight()] + objects.get(i - 1).getCost();
                    if (c1 > c2) {
                       list[i][j] = list[i - 1][j];
                    } else {
                        list[i][j] = list[i - 1][j - objects.get(i - 1).getWeight()] + " " +objects.get(i - 1).getName();
                    }
                }
            }
        }

        return new Holder(Arrays.copyOfRange(mass, 1, mass.length), list[N][maxWeight]);
    }
}
