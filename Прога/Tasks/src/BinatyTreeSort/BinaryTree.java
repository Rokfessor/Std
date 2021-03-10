package BinatyTreeSort;

import Sorting.Element;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTree {
    Node root;
    int count = 0;

    public void add(Element value) {
        root = add(root, value);
        count++;
    }

    private Node add(Node node, Element value) {
        if (node == null)
            return new Node(value);

        if (node.value.index < value.index) {
            node.right = add(node.right, value);
        } else {
            node.left = add(node.left, value);
        }

        return node;
    }

    private void getSorted(Node node, int[] mass) {
        if (node != null) {
            getSorted(node.left, mass);
            mass[Temp.i] = node.value.index;
            Temp.i++;
            getSorted(node.right, mass);
        }
    }

    private void getSorted(Node node, ArrayList<Element> list) {
        if (node != null) {
            getSorted(node.left, list);
            list.add(node.value);
            getSorted(node.right, list);
        }
    }

    public void sort(Element[] mass) {
        for (Element element : mass) {
            add(element);
        }

        int[] m = new int[count];
        Temp.i = 0;
        getSorted(root, m);
    }
}
