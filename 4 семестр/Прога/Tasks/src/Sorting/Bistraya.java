package Sorting;

public class Bistraya {
    public static long sort(Element[] mass) {
        long time = System.currentTimeMillis();
        sort(mass, 0, mass.length - 1);
        return System.currentTimeMillis() - time;
    }

    private static void sort(Element[] mass, int low, int high) {
        if (mass.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = mass[middle].index;

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (mass[i].index < opora) {
                i++;
            }

            while (mass[j].index > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                Util.swap(mass, i, j);
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            sort(mass, low, j);

        if (high > i)
            sort(mass, i, high);
    }

    public static Counter sortAndGetData(Element[] mass) {
        Counter counter = sortRepAndCom(mass);
        counter.name = "Быстрая сортировка";
        counter.time = sort(mass);

        return counter;
    }

    public static Counter sortRepAndCom(Element[] mass) {
        Element[] m = mass.clone();
        Counter counter = new Counter();
        sortRepAndCom(mass, 0, mass.length - 1, counter);
        counter.time = sort(m);
        return counter;
    }

    private static void sortRepAndCom(Element[] mass, int low, int high, Counter counter) {
        if (mass.length == 0) {
            counter.comparisons++;
            return;//завершить выполнение если длина массива равна 0
        }

        if (low >= high) {
            counter.comparisons++;
            return;//завершить выполнение если уже нечего делить
        }

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = mass[middle].index;

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            counter.comparisons++;

            while (mass[i].index < opora) {
                counter.comparisons++;
                i++;
            }
            counter.comparisons++;

            while (mass[j].index > opora) {
                counter.comparisons++;
                j--;
            }
            counter.comparisons++;

            if (i <= j) {//меняем местами
                Util.swap(mass, i, j);
                counter.replacements++;
                i++;
                j--;
            }
        }
        counter.comparisons++;

        // вызов рекурсии для сортировки левой и правой части
        if (low < j) {
            counter.comparisons++;
            sortRepAndCom(mass, low, j, counter);
        }

        if (high > i) {
            counter.comparisons++;
            sortRepAndCom(mass, i, high, counter);
        }
    }

}

