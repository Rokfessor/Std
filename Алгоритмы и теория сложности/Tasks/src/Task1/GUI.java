package Task1;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class GUI {
    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField textArea = new JTextField();
        JButton button = new JButton("Посчитать");
        JLabel label = new JLabel();
        button.addActionListener(e -> label.setText(Arrays.toString(Task1.startByString(textArea.getText()))));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setMaximumSize(new Dimension(1000, 20));
        panel.add(textArea);
        panel.add(button);
        panel.add(label);
        panel.add(new Painter(500,500));
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setMinimumSize(new Dimension(500, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
