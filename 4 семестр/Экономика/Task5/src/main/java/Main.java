import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Utils.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUI gui = new GUI();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setTitle("Task5");
        gui.setSize(new Dimension(300, 300));
        gui.setVisible(true);
    }
}
