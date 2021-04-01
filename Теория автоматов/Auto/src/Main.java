import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Form gui = new Form();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500,500));
        gui.pack();
        gui.setVisible(true);

        /*String[] str = {"1101", "1100", "1110", "1111", "1010", "0011", "0111", "0110"};
        System.err.println(Calculator.calculate(new ArrayList<>(Arrays.asList(str))));*/
    }
}
