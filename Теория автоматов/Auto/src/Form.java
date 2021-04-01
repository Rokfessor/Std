import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    private JPanel panel;
    private JTextField rTF1;
    private JTextField rTF2;
    private JTextField rTF3;
    private JTextField rTF4;
    private JTextField rTF5;
    private JTextField rTF6;
    private JTextField rTF7;
    private JTextField rTF8;
    private JTextField a0TextField;
    private JTextField a0TextField10;
    private JTextField a1TextField4;
    private JTextField a1TextField5;
    private JTextField a1TextField6;
    private JTextField a1TextField8;
    private JTextField a0TextField8;
    private JTextField a1TextField7;
    private JTextField a1TextField9;
    private JTextField a1TextField3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField a1TextField1;
    private JTextField a1TextField;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField a0TextField3;
    private JTextField a0TextField2;
    private JTextField a0TextField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField7;
    private JTextField rTF0;

    public Form() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setContentPane(panel);

        JTextField[] tfs = {rTF0, rTF1, rTF2, rTF3, rTF4, rTF5, rTF6, rTF7, rTF8};

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tfs.length; i++){
                    StringBuilder sb = new StringBuilder(Integer.toBinaryString(i));
                    while (sb.length() < 3)
                        sb.insert(0, "0");

                    if (Integer.parseInt(tfs[i].getText()) == 1){

                    }
                }
            }
        };

        rTF0.addActionListener(listener);
        rTF1.addActionListener(listener);
        rTF2.addActionListener(listener);
        rTF3.addActionListener(listener);
        rTF4.addActionListener(listener);
        rTF5.addActionListener(listener);
        rTF6.addActionListener(listener);
        rTF7.addActionListener(listener);
        rTF8.addActionListener(listener);
    }
}
