import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class IncidenceList {
    List<Element> list;

    public IncidenceList() {
        list = new ArrayList<>();
    }

    public boolean addTo(int elem, int val) {
        for (Element e : list) {
            if (e.value == elem) {
                while (e.hasNext())
                    e = e.next;

                e.next = new Element(val);
                return true;
            }
        }
        return false;
    }

    public boolean addNew(int val) {
        for (Element e : list) {
            if (e.value == val)
                return false;
        }
        list.add(new Element(val));
        return true;
    }

    private class Element {
        @Getter
        private final int value;
        @Getter
        private Element next;

        private Element(int val) {
            value = val;
            next = null;
        }

        public boolean hasNext() {
            return next != null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element e : list) {
            while (e.hasNext()) {
                sb.append(e.getValue()).append("->");
                e = e.next;
            }
            sb.append(e.getValue()).append("->null\n");
        }
        return sb.toString();
    }
}
