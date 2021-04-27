package sample;

import java.io.IOException;

public class Utils {
    public static Domino[] parse(String s) throws IOException {
        String[] m = s.split(" ");
        if (m.length > 28 || m.length == 0)
            throw new IOException("Введено правильное количество костей");
        Domino[] res = new Domino[m.length];
        for (int i = 0; i < m.length; i++) {
            String[] j = m[i].split(":");
            try {
                Domino domino = new Domino(Integer.parseInt(j[0]), Integer.parseInt(j[1]));
                boolean f = true;
                    for (Domino d : res) {
                        if (d != null && d.equals(domino)) {
                            throw new IOException("Данная кость уже есть в наборе! " + domino);
                        }
                } res[i] = new Domino(Integer.parseInt(j[0]), Integer.parseInt(j[1]));
            } catch (NumberFormatException e){
                throw new IOException("Введено правильное значение костей " +j[0] + "|" + j[1]);
            }
        }
        return res;
    }
}
