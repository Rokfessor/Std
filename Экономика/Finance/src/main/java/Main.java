import GUI.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MainForm mainForm = new MainForm();
        mainForm.setSize(600, 600);
        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainForm.setVisible(true);
    }
}
