package Task1;

import java.util.Arrays;

public class Task1 {
    public Task1(int size) {
        int[] m = new int[size];
        Arrays.fill(m, 1);
        System.err.println("\n" + Arrays.toString(start(m)));
    }

    public static int[] startByString(String line){
        int[] mass = new int[line.length()];
        for (int i = 0; i < mass.length; i++){
            mass[i] = line.charAt(i) - '0';
        }
        System.err.println(Arrays.toString(mass));
        return start(mass);
    }

    public static int[] start(int[] mass) {
        return q1(mass, 0);
    }

    private static int[] q1(int[] mass, int i) {
        System.err.print("q1 -> ");
        if (mass[i] == 1) {
            mass[i] = 0;
            i--;
            return q2(mass, i);
        }
        return null;
    }

    private static int[] q2(int[] mass, int i) {
        System.err.print("q2 ->");
        i = 0;
        mass = prepareMass(mass);
        mass[i] = 1;
        return q3(mass, i);
    }

    private static int[] q3(int[] mass, int i) {
        System.err.print("q3 ->");
        for (; i < mass.length; i++) {
            if (mass[i] != 1)
                break;
        }
        return q4(mass, i);
    }

    private static int[] q4(int[] mass, int i) {
        System.err.print("q4 ->");
        boolean f = true;
        for (; i < mass.length; i++) {
            if (mass[i] != 0) {
                return q1(mass, i);
            }
        }
        return q5(mass, i);
    }

    private static int[] q5(int[] mass, int i) {
        System.err.print("q5 ->");
        for (int j = mass.length - 1; j >= 0; j--) {
            mass[j] = 1;
        }
        return mass;
    }


    private static int[] prepareMass(int[] mass) {
        mass = Arrays.copyOf(mass, mass.length + 1);
        for (int i = mass.length - 1; i > 0; i--) {
            mass[i] = mass[i - 1];
        }
        mass[0] = 0;

        return mass;
    }
}
