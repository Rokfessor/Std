import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame implements CalcListener {
    private JTextPane KRATNTextPane = new JTextPane();
    private JTextPane SOLTextPane = new JTextPane();
    private JTextField weightTextField = new JTextField("13");
    private JTextArea resultTextArea = new JTextArea();
    private StyleContext sc = new StyleContext();
    private DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    private Style st = sc.addStyle("color", null);
    private JLabel napLabel = new JLabel("Направление: ");

    GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel panel = (JPanel) getContentPane();
        JPanel pan = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel weightLabel = new JLabel("Необходимый вес:");
        JLabel kettLabel = new JLabel("Развесовка:");
        JTextField kettTextField = new JTextField("1 2 3 4 10");
        JButton button = new JButton("Calculate");
        JLabel KRATNLabel = new JLabel("KRATN");
        JLabel SOLLabel = new JLabel("SOL");
        JLabel resultlabel = new JLabel("Result");
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        weightLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        resultTextArea.setEditable(false);
        KRATNTextPane.setEditable(false);
        SOLTextPane.setEditable(false);

        weightTextField.setMaximumSize(new Dimension(300, 20));
        kettTextField.setMaximumSize(new Dimension(300, 20));
        KRATNTextPane.setMaximumSize(new Dimension(300, 20));
        SOLTextPane.setMaximumSize(new Dimension(300, 40));
        scrollPane.setMaximumSize(new Dimension(300, 200));

        button.addActionListener(e -> {
            String[] str = kettTextField.getText().split(" ");
            int[] m = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                m[i] = Integer.parseInt(str[i]);
            }
            final String[] res = new String[1];
            Thread thread = new Thread(() -> {
                //Переводим строку из вида "12334555" в "11213", где индекс - это число, а значение - количество
                String[] s = kettTextField.getText().split(" ");
                System.err.println(Arrays.toString(s));
                int[] mass = new int[2];
                for (int i = 0; i < s.length; i++) {
                    int a = Integer.parseInt(String.valueOf(s[i]));
                    if (mass.length - 1 < a) {
                        mass = Arrays.copyOf(mass, a);
                    }
                    mass[a - 1]++;
                }
                //
                try {
                    res[0] = Calculator.calculate(Integer.parseInt(weightTextField.getText()), mass);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            thread.start();
            resultTextArea.setText(res[0]);
        });
        panel.add(weightLabel);
        panel.add(weightTextField);
        panel.add(kettLabel);
        panel.add(kettTextField);
        panel.add(button);
        panel.add(KRATNLabel);
        panel.add(KRATNTextPane);
        panel.add(SOLLabel);
        panel.add(SOLTextPane);
        panel.add(napLabel);
        panel.add(resultlabel);
        panel.add(scrollPane);
    }

    @Override
    public void stateChanged(int[] mass1, int[] mass2, boolean napravl) {
        SwingUtilities.invokeLater(() -> {
            int V = 0;
            StringBuilder sb = new StringBuilder(Arrays.toString(mass2) + " = ");
            for (int i = 1; i <= mass2.length; i++) {
                V += i * mass2[i - 1];
                for (int j = 0; j < mass2[i - 1]; j++) {
                    sb.append(i).append(" + ");
                }
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("= ").append(V);

            if (napravl)
                napLabel.setText("Прямой проход");
            else
                napLabel.setText("Возврат");

            try {
                doc.remove(0, doc.getLength());
                doc.insertString(0, sb.toString(), null);
                if (V == Integer.parseInt(weightTextField.getText()))
                    st.addAttribute(StyleConstants.Foreground, Color.green);
                else
                    st.addAttribute(StyleConstants.Foreground, Color.red);
                int len = Integer.toString(V).length();
                doc.setCharacterAttributes(doc.getLength() - len, len, st, false);
                st.addAttribute(StyleConstants.Foreground, Color.red);
                KRATNTextPane.setText(Arrays.toString(mass1));
                SOLTextPane.setDocument(doc);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setResult(String res) {
        SwingUtilities.invokeLater(() -> {
            resultTextArea.setText(res);
        });
    }
}
