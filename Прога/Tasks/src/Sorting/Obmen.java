package Sorting;

public class Obmen {
    public static long sort(Element[] mass) {
        long start = System.currentTimeMillis();
        for (int i = mass.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (mass[j].index > mass[j + 1].index) {
                    Util.swap(mass, j, j + 1);
                }
            }
        }
        return System.currentTimeMillis() - start;
    }

    public static Counter sortAndGetData(Element[] mass){
        Element[] m = mass.clone();
        Counter counter = sortRepAndCom(mass);
        counter.name = "Сортировка обменом";
        counter.time = sort(m);

        return counter;
    }

    public static Counter sortRepAndCom(Element[] mass){
        Counter counter = new Counter();
        for (int i = mass.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                counter.comparisons++;
                if (mass[j].index > mass[j + 1].index) {
                    Util.swap(mass, j, j + 1);
                    counter.replacements++;
                }
            }
        }
        return counter;
    }
}
