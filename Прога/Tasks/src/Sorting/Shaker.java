package Sorting;

public class Shaker {
    public static long sort(Element[] mass) {
        long start = System.currentTimeMillis();
        int left = 0;
        int right = mass.length - 1;
        do {
            for (int i = left; i < right; i++) {
                if (mass[i].index > mass[i + 1].index) {
                    Util.swap(mass, i, i + 1);
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (mass[i].index < mass[i - 1].index) {
                    Util.swap(mass, i, i - 1);
                }
            }
            left++;
        } while (left < right);
        return System.currentTimeMillis() - start;
    }

    public static Counter sortAndGetData(Element[] mass){
        Element[] m = mass.clone();
        Counter counter = sortRepAndCom(mass);
        counter.name = "Шейкерная сортировка";
        counter.time = sort(m);

        return counter;
    }

    public static Counter sortRepAndCom(Element[] mass){
        Counter counter = new Counter();
        int left = 0;
        int right = mass.length - 1;
        do {
            for (int i = left; i < right; i++) {
                counter.comparisons++;
                if (mass[i].index > mass[i + 1].index) {
                    Util.swap(mass, i, i + 1);
                    counter.replacements++;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                counter.comparisons++;
                if (mass[i].index < mass[i - 1].index) {
                    Util.swap(mass, i, i - 1);
                    counter.replacements++;
                }
            }
            left++;

            counter.comparisons++;
        } while (left < right);
        return counter;
    }
}
