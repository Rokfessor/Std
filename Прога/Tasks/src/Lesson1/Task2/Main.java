package Lesson1.Task2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] mass = {
                {0, 1, 0.5, 1},
                {0, 0, 0, 0.5},
                {0.5, 1, 0, 1},
                {0, 0.5, 0, 0}
        };
        double[] score = new double[mass.length];
        double[] berger = new double[mass.length];

        //System.err.println(Arrays.deepToString(mass));

        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length; j++) {
                score[i] += mass[i][j];
            }
        }

        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass.length; j++) {
                berger[i] += score[j] * mass[i][j];
            }
        }

        for (int i = 0; i < score.length - 1; i++) {
            for (int j = i + 1; j < score.length; j++) {
                if (score[i] < score[j]) {
                    swap(score, i, j);
                    swap(berger, i, j);
                    swapRows(mass, i, j);
                    swapColumns(mass, i, j);
                }
                if (score[i] == score[j] && berger[i] < berger[j]) {
                    swap(score, i, j);
                    swap(berger, i, j);
                    swapColumns(mass, i, j);
                }
            }
        }

        prettyPrint(mass, score, berger);
    }

    private static void prettyPrint(double[][] mass, double[] score, double[] berger) {
        int place = 1;
        for (int i = 0; i < mass.length; i++) {
            if (i != 0) {
                if (score[i] != score[i - 1] || berger[i] != berger[i - 1]) {
                    place = i + 1;
                }
            }
            System.err.println(Arrays.toString(mass[i]) + " Place:" + place + " Score:" + score[i] + " Berger:" + berger[i]);
        }
    }

    private static void swap(double[] mass, int elem1, int elem2) {
        double tmp = mass[elem1];
        mass[elem1] = mass[elem2];
        mass[elem2] = tmp;
    }

    private static void swapColumns(double[][] mass, int col1, int col2) {
        for (double[] mas : mass) {
            swap(mas, col1, col2);
        }
    }

    private static void swapRows(double[][] mass, int row1, int row2) {
        double[] tmp = mass[row1];
        mass[row1] = mass[row2];
        mass[row2] = tmp;
    }
}
