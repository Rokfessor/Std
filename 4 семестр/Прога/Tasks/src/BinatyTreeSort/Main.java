package BinatyTreeSort;

import Sorting.Element;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int len = 10;
        Element[] mass = new Element[len];
        for (int i = 0; i < len; i++) {
            mass[i] = new Element();
        }

        randomizeMass(mass);

        System.err.println(Arrays.toString(mass));
        System.err.println("\n");

        BinaryTree tree = new BinaryTree();
        tree.sort(mass);
        System.err.println(Arrays.toString(mass));
    }

    public static String generateString(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.substring(0, length);
    }

    public static void randomizeMass(Element[] mass) {
        for (Element element : mass) {
            element.index = (int) (Math.random() * mass.length * 10);
            element.line = generateString(64);
        }
    }
}
