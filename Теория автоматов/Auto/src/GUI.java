import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JPanel panel = (JPanel)getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JPanel contPanel = new JPanel();
        JPanel paintPanel = new JPanel();
        panel.add(contPanel);
        panel.add(paintPanel);

        contPanel.setLayout(new BoxLayout(contPanel, BoxLayout.Y_AXIS));
        contPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField rTF1 = new JTextField("1");
        JTextField rTF2 = new JTextField("1");
        JTextField rTF3 = new JTextField("1");
        JTextField rTF4 = new JTextField("1");
        JTextField rTF5 = new JTextField("1");
        JTextField rTF6 = new JTextField("1");
        JTextField rTF7 = new JTextField("1");
        JTextField rTF8 = new JTextField("1");
        JTextField[] tmp = {rTF1, rTF2, rTF3, rTF4, rTF5, rTF6, rTF7, rTF8};

        for (int i = 0; i < 8; i++) {
            StringBuilder n = new StringBuilder(Integer.toBinaryString(i));
            int w = 100, h = 30;
            while (n.length() < 3)
                n.insert(0, "0");

            JPanel p = new JPanel();
            GridLayout layout = new GridLayout(9, 4, 10, 15);
            p.setLayout(layout);
            JTextField tf1 = new JTextField(String.valueOf(n.charAt(0)));
            tf1.setMaximumSize(new Dimension(w, h));
            tf1.setMinimumSize(new Dimension(w, h));
            tf1.setEditable(false);
            JTextField tf2 = new JTextField(String.valueOf(n.charAt(1)));
            tf2.setEditable(false);
            tf2.setMaximumSize(new Dimension(w, h));
            tf2.setMinimumSize(new Dimension(w, h));
            JTextField tf3 = new JTextField(String.valueOf(n.charAt(2)));
            tf3.setEditable(false);
            tf3.setMaximumSize(new Dimension(w, h));
            tf3.setMinimumSize(new Dimension(w, h));

            tmp[i].setMaximumSize(new Dimension(w, h));
            tmp[i].setMinimumSize(new Dimension(w, h));

            p.add(tf1);
            p.add(tf2);
            p.add(tf3);
            p.add(tmp[i]);
            contPanel.add(p);
        }


    }
}
