import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setSize(new Dimension(500, 500));
        gui.setResizable(false);
        gui.pack();
        gui.setVisible(true);
    }
}
