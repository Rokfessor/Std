import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel panel = (JPanel) getContentPane();
        getContentPane().setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel weightLabel = new JLabel("Введите вес, который нужно набрать:");
        JTextField weightTextField = new JTextField();
        JLabel kettLabel = new JLabel("Введите развесовку:");
        JTextField kettTextField = new JTextField();
        JScrollPane scrollPane = new JScrollPane();


        panel.add(weightLabel);
        panel.add(weightTextField);
        panel.add(kettLabel);
        panel.add(kettTextField);

        panel.add(scrollPane);

    }
}
