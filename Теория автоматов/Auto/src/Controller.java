import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    public static DataHolder convertAndCalculate(int[] data) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toBinaryString(i + 8));
            sb.delete(0, 1);

            if (data[i] == 1) {
                list.add(sb.toString());
            }
        }
        System.err.println(list);

        for (int a : data){
            if (a != 1) {
                System.err.println(Calculator.calculate(list));
                return new DataHolder(list, Calculator.calculate(list));
            }
        }

        return new DataHolder(list, new ArrayList<>(Collections.singletonList("111")));
    }
}
