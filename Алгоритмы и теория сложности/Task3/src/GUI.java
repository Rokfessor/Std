import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame implements CalcListener {
    private JTextField VTextPane = new JTextField();
    private JTextField ContTextPane = new JTextField();
    private JTextField OptContTextField = new JTextField();
    private JTextArea resultTextArea = new JTextArea();
    private StyleContext sc = new StyleContext();
    private DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    private Style st = sc.addStyle("color", null);

    GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel weightLabel = new JLabel("Вес предметов");
        JTextArea weightTextArea = new JTextArea("0.2 0.3 0.4 0.6");
        weightTextArea.setMaximumSize(new Dimension(1000, 40));
        JButton button = new JButton("Calculate");
        JLabel VLabel = new JLabel("V");
        JLabel ContLabel = new JLabel("Cont");
        JLabel OptContLabel = new JLabel("OptCont");

        resultTextArea.setEditable(false);
        OptContTextField.setEditable(false);
        VTextPane.setEditable(false);
        ContTextPane.setEditable(false);

        OptContTextField.setMaximumSize(new Dimension(300, 20));
        VTextPane.setMaximumSize(new Dimension(300, 20));
        ContTextPane.setMaximumSize(new Dimension(300, 20));

        button.addActionListener(e -> {
            String[] str = weightTextArea.getText().split(" ");
            double[] m = new double[str.length];
            for (int i = 0; i < str.length; i++) {
                m[i] = Double.parseDouble(str[i]);
            }
            Thread thread = new Thread(() -> {
                try {
                    Calculator.calculate(m);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            thread.start();
        });
        panel.add(weightLabel);
        panel.add(weightTextArea);
        panel.add(button);
        panel.add(OptContLabel);
        panel.add(OptContTextField);
        panel.add(VLabel);
        panel.add(VTextPane);
        panel.add(ContLabel);
        panel.add(ContTextPane);
        panel.add(new JLabel("result"));
        panel.add(resultTextArea);
    }

    @Override
    public void stateChanged(double[] mass1, int[] mass2, int[] mass3) {
        SwingUtilities.invokeLater(() -> {
            VTextPane.setText(Arrays.toString(mass1));
            ContTextPane.setText(Arrays.toString(mass2));
            OptContTextField.setText(Arrays.toString(mass3));
        });
    }

    @Override
    public void setResult(String res) {
        SwingUtilities.invokeLater(() -> {
            resultTextArea.setText(res);
        });
    }
}
