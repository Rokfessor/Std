package Sorting;

public class Util {
    public static void swap(Element[] mass, int i, int j){
        Element tmp = mass[i];
        mass[i] = mass[j];
        mass[j] = tmp;
    }
}
