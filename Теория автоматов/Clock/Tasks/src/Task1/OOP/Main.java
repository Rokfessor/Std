package Task1.OOP;

import Task1.OOP.GUI.Window;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        frame.setTitle("Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(220, 100));
        frame.setResizable(false);
        frame.setContentPane(new Window());
        frame.setVisible(true);
    }
}
