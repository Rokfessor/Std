package BinatyTreeSort;

import Sorting.Element;

import java.util.Objects;

public class Node {
    Element value;
    Node left;
    Node right;

    Node(Element value){
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        return this.value.index == ((Node) o).value.index;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
