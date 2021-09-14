import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        Calculator.setListener(gui);
        gui.setSize(new Dimension(200, 450));
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //gui.setResizable(false);
        gui.setVisible(true);
    }
}
