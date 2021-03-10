package Lesson2;

import java.util.Arrays;

public class Main {
    static int[] mass;
    public static void main(String[] args) {
        final int N = 1000000;
        final int x = -1;
        final long count = 5000;
        mass = new int[N];

        //System.out.format("%.3f", number);

        for (int i = 0; i < N; i++) {
            mass[i] = i;
        }

        long time = System.currentTimeMillis();
        for (int j = 0; j < count; j++) {
            int i = 0;
            do {
                i++;
            } while (!(mass[i] == x || i == mass.length - 1));
        }
        time = System.currentTimeMillis() - time;
        System.err.println("Линейный: " + (time / (double)count) + " миллисекунд");

        int[] mass2 = Arrays.copyOf(mass, N + 1);

        time = System.currentTimeMillis();
        for (int j = 0; j < count; j++) {
            int i = 0;mass2[N] = x;
            do {
                i++;
            } while (mass2[i] != x);
        }
        time = System.currentTimeMillis() - time;
        System.err.println("Фиктивнй: " +  (time / (double)count) + " миллисекунд");

        time = System.currentTimeMillis();
        for (int j = 0; j < count; j++) {
            int i = 0, l = N - 1, k;
            do {
                k = (i + l) / 2;
                if (i > mass[k])
                    i = k + 1;
                else
                    l = k - 1;
            } while (!(mass[k] == x || i > l));
        }
        time = System.currentTimeMillis() - time;
        System.err.println("Бинарный: "  + (time / (double)count) + " миллисекунд");
    }
}