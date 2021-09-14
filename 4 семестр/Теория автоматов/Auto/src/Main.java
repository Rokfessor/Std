import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Form gui = new Form();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(620,500));
        gui.pack();
        gui.setVisible(true);
    }
}
