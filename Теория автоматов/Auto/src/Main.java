import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] str = {"110", "110", "111", "111", "101", "001", "011", "011"};
        System.err.println(Calculator.calculate(new ArrayList<>(Arrays.asList(str))));
    }
}
