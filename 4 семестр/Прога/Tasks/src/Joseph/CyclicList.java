package Joseph;

public class CyclicList<E> {
    private Node<E> last;
    private int size = 0;

    public Node<E> getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void add(E value) {
        if (last == null) {
            last = new Node<>(value);
            last.next = last;
        } else {
            Node<E> node = new Node<>(value);
            node.next = last.next;
            last.next = node;
            last = node;
        }
        size++;
    }

    /*
    public void swap(Node<E> node1, Node<E> node2) {
        Node<E> current = last;
        Node<E> tmp;
        do {
            if (current == node1 || current == node2) {
                tmp
            }
            current = current.next;
        } while (current != last);
    }
     */

    public void delete(Node<E> node) {
        Node<E> current = last;
        do {
            if (current.next == node) {
                current.next = current.next.next;
                size--;
                break;
            }
            current = current.next;
        } while (current != last);
        if (node == last){
            last = current;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = last.next;
        do {
            sb.append(node.value).append(", ");
            node = node.next;
        } while (node != last.next);
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.deleteCharAt(sb.lastIndexOf(" "));
        sb.append(".");
        return sb.toString();
    }
}
