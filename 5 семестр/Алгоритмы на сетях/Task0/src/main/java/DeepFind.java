import java.util.LinkedHashSet;
import java.util.Set;

public class WidthFind {
    IncidenceList list;
    Set<Integer> set = new LinkedHashSet<>();
    public void find (IncidenceList list) {
        this.list = list;
        calc(list.getById(0));
    }

    private void calc(IncidenceList.Element e) {
        while (e != null) {
            if (!set.contains(e.getValue())) {
                System.err.println(e.getValue());
                set.add(e.getValue());
                if (e.getNext() != null)
                    calc(list.get(e.getNext().getValue()));
            }
            e = e.getNext();
        }
    }
}
