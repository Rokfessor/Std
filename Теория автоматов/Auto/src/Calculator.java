import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    public static ArrayList<String> calculate(ArrayList<String> data) {
        String[] newData = Arrays.copyOf(data.toArray(), data.size(), String[].class);
        int size = newData.length;
        ArrayList<String> stack = new ArrayList<>();
        String[] mark = repeatelem(0, size);


        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                String c = combine(newData[i], newData[j]);
                if (!c.equals("")) {
                    stack.add(c);
                    mark[i] = "1";
                    mark[j] = "1";
                }
            }
        }

        String[] im = stack.toArray(new String[stack.size()]);
        String[] mark2 = repeatelem(0, im.length);
        for (int i = 0; i < im.length; i++) {
            for (int j = i + 1; j < im.length; j++) {
                if ((i != j) && (mark2[j].equals("0")) && (im[i].equals(im[j]))) {
                    mark2[j] = "1";
                }
            }
        }

        ArrayList<String> im2 = new ArrayList<>();
        for (int i = 0; i < im.length; i++) {
            if (mark2[i].equals("0")) {
                im2.add(im[i]);
            }
        }

        int m = 0;
        ArrayList<String> IM = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (mark[i].equals("0")) {
                IM.add(newData[i]);
                m++;
            }
        }

        if ((m != size) && (size != 1)) {
            IM.addAll(calculate(im2));
        }

        return IM;
    }

    private static String[] addOneAndRecurse(ArrayList<String> st, int remaining, int elem) {
        st.add(String.valueOf(elem));
        if (remaining > 1)
            addOneAndRecurse(st, remaining - 1, elem);


        return st.toArray(new String[0]);
    }

    private static String[] repeatelem(int elem, int count) {
        ArrayList<String> st = new ArrayList<>();
        return addOneAndRecurse(st, count, elem);
    }

    private static String combine(String m, String n) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) == n.charAt(i)) {
                sb.append(m.charAt(i));
            } else if (m.charAt(i) != n.charAt(i)) {
                sb.append('-');
            }
            count++;
        }

        if (count > 1) {
            return "";
        }

        return sb.toString();
    }
}
