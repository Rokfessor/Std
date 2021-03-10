package Sorting;

public class Vibor {
    public static long sort(Element[] mass) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < mass.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < mass.length; j++) {
                if (mass[j].index < mass[min].index) {
                    min = j;
                }
            }
            Util.swap(mass, i, min);
        }
        return System.currentTimeMillis() - start;
    }

    public static Counter sortAndGetData(Element[] mass){
        Element[] m = mass.clone();
        Counter counter = sortRepAndCom(mass);
        counter.name = "Сортировка выборкой";
        counter.time = sort(m);

        return counter;
    }

    public static Counter sortRepAndCom(Element[] mass){
        Counter counter = new Counter();
        for (int i = 0; i < mass.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < mass.length; j++) {
                counter.comparisons++;
                if (mass[j].index < mass[min].index) {
                    min = j;
                }
            }
            Util.swap(mass, i, min);
            counter.replacements++;
        }
        return counter;
    }

}
