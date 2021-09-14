import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        Calculator.setListener(gui);
        ApproxCalculator.setListener(gui);
        gui.setSize(new Dimension(400, 350));
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setResizable(true);
        gui.setVisible(true);
    }
}
