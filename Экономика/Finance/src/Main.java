import GUI.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            MainForm mainForm = new MainForm();
            mainForm.setSize(1280, 720);
            mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainForm.setVisible(true);
    }
}
