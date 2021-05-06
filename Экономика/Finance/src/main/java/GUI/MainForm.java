package GUI;

import javax.swing.*;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    JPanel panel;

    public MainForm() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        panel = (JPanel) getContentPane();
        panel.add(new LoginForm());
    }
}
