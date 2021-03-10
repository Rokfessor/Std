package Sorting;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
    public static String generateString(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.substring(0, length);
    }

    public static void reverseMass(Element[] mass) {
        int i = mass.length;
        for (Element element : mass) {
            element.index = i;
            element.line = generateString(64);
            i--;
        }
    }

    public static void randomizeMass(Element[] mass) {
        for (Element element : mass) {
            element.index = (int) (Math.random() * mass.length);
            element.line = generateString(64);
        }
    }

    public static void main(String[] args) {
        final int len = 10000;
        Element[] mass = new Element[len];
        for (int i = 0; i < len; i++) {
            mass[i] = new Element();
        }

        randomizeMass(mass);
        System.err.println("--Рандомный массив-- \n" + Bistraya.sortAndGetData(mass).toString());
        System.err.println("--Отсортированный массив-- \n" + Bistraya.sortAndGetData(mass).toString());
        reverseMass(mass);
        System.err.println("--Обратный массив-- \n" + Bistraya.sortAndGetData(mass).toString());

        System.err.println("==============");

        randomizeMass(mass);
        System.err.println("--Рандомный массив-- \n" + Piramida.sortAndGetData(mass).toString());
        System.err.println("--Отсортированный массив-- \n" + Piramida.sortAndGetData(mass).toString());
        reverseMass(mass);
        System.err.println("--Обратный массив-- \n" + Piramida.sortAndGetData(mass).toString());

        System.err.println("==============");

        randomizeMass(mass);
        System.err.println("--Рандомный массив-- \n" + Shaker.sortAndGetData(mass).toString());
        System.err.println("--Отсортированный массив-- \n" + Shaker.sortAndGetData(mass).toString());
        reverseMass(mass);
        System.err.println("--Обратный массив-- \n" + Shaker.sortAndGetData(mass).toString());

        System.err.println("==============");

        randomizeMass(mass);

        System.err.println("--Рандомный массив-- \n" + Vstavka.sortAndGetData(mass).toString());
        System.err.println("--Отсортированный массив-- \n" + Vstavka.sortAndGetData(mass).toString());
        reverseMass(mass);
        System.err.println("--Обратный массив-- \n" + Vstavka.sortAndGetData(mass).toString());

        System.err.println("==============");

        randomizeMass(mass);
        System.err.println("--Рандомный массив-- \n" + Vibor.sortAndGetData(mass).toString());
        System.err.println("--Отсортированный массив-- \n" + Vibor.sortAndGetData(mass).toString());
        reverseMass(mass);
        System.err.println("--Обратный массив-- \n" + Vibor.sortAndGetData(mass).toString());

        System.err.println("==============");

        randomizeMass(mass);
        System.err.println("--Рандомный массив-- \n" + Obmen.sortAndGetData(mass).toString());
        System.err.println("--Отсортированный массив-- \n" + Obmen.sortAndGetData(mass).toString());
        reverseMass(mass);
        System.err.println("--Обратный массив-- \n" + Obmen.sortAndGetData(mass).toString());
    }
}
