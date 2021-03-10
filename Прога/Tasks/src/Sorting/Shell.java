package Sorting;

public class Shell {
    public static int sort(Element[] mass) {
        int counter = 0;
        for (int step = mass.length / 2; step > 0; step /= 2) {
            for (int i = step; i < mass.length; i++) {
                for (int j = i - step; j >= 0 && mass[j].index > mass[j + step].index; j -= step) {
                    Util.swap(mass, j, j + step);
                    counter ++;
                }
            }
        }
        return counter;
    }
}
