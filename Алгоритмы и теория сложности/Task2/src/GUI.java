import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame implements CalcListener {
    private JTextPane KRATNTextArea = new JTextPane();
    private JTextPane SOLTextArea = new JTextPane();
    private JTextField weightTextField = new JTextField("9");
    private JTextArea resultTextArea = new JTextArea();

    GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel panel = (JPanel) getContentPane();
        getContentPane().setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel weightLabel = new JLabel("Необходимый вес:");
        JLabel kettLabel = new JLabel("Развесовка:");
        JTextField kettTextField = new JTextField("11111");
        JButton button = new JButton("Calculate");
        JLabel KRATNLabel = new JLabel("KRATN");
        JLabel SOLLabel = new JLabel("SOL");
        JLabel resultlabel = new JLabel("Result");
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        resultTextArea.setEditable(false);
        KRATNTextArea.setEditable(false);
        SOLTextArea.setEditable(false);

        weightTextField.setMaximumSize(new Dimension(300, 20));
        kettTextField.setMaximumSize(new Dimension(300, 20));
        KRATNTextArea.setMaximumSize(new Dimension(300,20));
        SOLTextArea.setMaximumSize(new Dimension(300,20));
        scrollPane.setMaximumSize(new Dimension(300,200));

        button.addActionListener(e -> {
            String[] str = kettTextField.getText().split("");
            int[] m = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                m[i] = Integer.parseInt(str[i]);
            }
            final String[] res = new String[1];
            Thread thread =  new Thread(() -> {
                //Переводим строку из вида "12334555" в "11213", где индекс - это число, а значение - количество
                String s = kettTextField.getText();
                int[] mass = new int[2];
                for (int i = 0; i < s.length(); i++) {
                    int a = Integer.parseInt(String.valueOf(s.charAt(i)));
                    System.err.println(a);
                    if (mass.length - 1 < a){
                        System.err.println("11");
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
        panel.add(KRATNTextArea);
        panel.add(SOLLabel);
        panel.add(SOLTextArea);
        panel.add(resultlabel);
        panel.add(scrollPane);
    }

    @Override
    public void stateChanged(int[] mass1, int[] mass2) {
        SwingUtilities.invokeLater(() -> {
            int V = 0;
            StringBuilder sb = new StringBuilder(Arrays.toString(mass2) + " = ");
            for (int i = 1; i <= mass2.length; i++) {
                V += i * mass2[i-1];
                for (int j = 0; j < mass2[i-1]; j++) {
                    sb.append(i).append(" + ");
                }
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("= ").append(V);

            KRATNTextArea.setText(Arrays.toString(mass1));
            SOLTextArea.setText(sb.toString());
        });
    }

    @Override
    public void setResult(String res) {
        SwingUtilities.invokeLater(() -> {
            resultTextArea.setText(res);
        });
    }
}
