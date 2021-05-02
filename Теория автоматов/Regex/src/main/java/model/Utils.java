package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static int getStartIndex(int n, String line) {
        //ищет n'ное вхождение " " в line
        Pattern p = Pattern.compile(" ");
        Matcher m = p.matcher(line);
        int counter = 0;
        while (m.find()) {
            if (counter == n - 1)
                return m.start();
            counter++;
        }

        return 0;
    }
}
