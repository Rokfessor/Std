package Sorting;

import java.util.Arrays;

public class Vstavka {
    public static long sort(Element[] mass) {
        long start = System.currentTimeMillis();
        for (int i = 1; i < mass.length; i++) {
            int a = i;
            while (a >= 1 && mass[a].index < mass[a - 1].index) {
                Util.swap(mass, a, a - 1);
                a--;
            }
        }
        return System.currentTimeMillis() - start;
    }

    public static Counter sortAndGetData(Element[] mass){
        Element[] m = mass.clone();
        Counter counter = sortRepAndCom(mass);
        counter.name = "Сортировка вставкой";
        counter.time = sort(m);

        return counter;
    }
    
    public static Counter sortRepAndCom(Element[] mass){
        Counter counter = new Counter();
        for (int i = 1; i < mass.length; i++) {
            int a = i;

            while (a >= 1 && mass[a].index < mass[a - 1].index) {
                Util.swap(mass, a, a - 1);
                a--;
                counter.replacements++;
                counter.comparisons++;
            }
            counter.comparisons++;
        }
        return counter;
    }
}
