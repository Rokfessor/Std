package Joseph;

public class Main {
    public static void main(String[] args) {
        CyclicList<Integer> list = new CyclicList<>();
        final int len = 5;
        for (int i = 1; i <= len; i++) {
            list.add(i);
        }
        System.err.println(joseph(list, 3, 3));
    }

    static int joseph(CyclicList<Integer> list, int step, int start) {
        if (start > list.getSize())
            return 0;

        Node<Integer> node = list.getLast();
        while (node.value != (start - 1 <= 0 ? start - 1 + list.getSize() :start - 1)){
            node = node.next;
        }

        while (list.getSize() != 1) {
            for (int i = 0; i < step; i++){
                node = node.next;
            }
            System.err.println(node.value);
            list.delete(node);
        }
        return list.getLast().value;
    }
}
