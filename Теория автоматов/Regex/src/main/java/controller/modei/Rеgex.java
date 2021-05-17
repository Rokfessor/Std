package controller.modei;

import java.util.ArrayList;
import java.util.List;

public class Rеgex {
    public static List<Integer> t33(String text) {
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int a1 = 0;
            int a2 = 0;
            for (char a : line.toCharArray()) {
                if (a == '1')
                    a1++;
                if (a == '0')
                    a2++;
            }
            if (a1 % 2 == 0 && a2 % 5 == 0)
                res.add(i);
        }
        return res;
    }
}
