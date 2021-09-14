package Sorting;

public class Piramida {
    public static long sort(Element[] mass) {
        long time = System.currentTimeMillis();
        int n = mass.length;
        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(mass, n, i);

        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            Util.swap(mass, 0, i);
            // Вызываем процедуру heapify на уменьшенной куче
            heapify(mass, i, 0);
        }
        return System.currentTimeMillis() - time;
    }

    // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i, что является
// индексом в mass[]. n - размер кучи
    private static void heapify(Element[] mass, int n, int i) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (l < n && mass[l].index > mass[largest].index)
            largest = l;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && mass[r].index > mass[largest].index)
            largest = r;
        // Если самый большой элемент не корень
        if (largest != i) {
            Util.swap(mass, i, largest);
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(mass, n, largest);
        }
    }

    public static Counter sortAndGetData(Element[] mass){
        Element[] m = mass.clone();
        Counter counter = sortRepAndCom(mass);
        counter.name = "Пирамидальная сортировка";
        counter.time = sort(m);

        return counter;
    }

    public static Counter sortRepAndCom(Element[] mass){
        Counter counter = new Counter();
        int n = mass.length;
        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyRepAndCom(mass, n, i, counter);

        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            Util.swap(mass, 0, i);
            counter.replacements++;
            // Вызываем процедуру heapify на уменьшенной куче
            heapifyRepAndCom(mass, i, 0, counter);
        }
        return counter;
    }

    private static void heapifyRepAndCom(Element[] mass, int n, int i, Counter counter) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (l < n && mass[l].index > mass[largest].index)
            largest = l;
        counter.comparisons += 2;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && mass[r].index > mass[largest].index)
            largest = r;
        counter.comparisons += 2;
        // Если самый большой элемент не корень
        if (largest != i) {
            counter.comparisons++;
            Util.swap(mass, i, largest);
            counter.replacements++;
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapifyRepAndCom(mass, n, largest, counter);
        }
    }
}
