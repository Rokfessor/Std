import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame implements CalcListener {
    private JTextField exactVTextPane = new JTextField();
    private JTextField exactContTextPane = new JTextField();
    private JTextField exactOptContTextField = new JTextField();
    private JTextArea exactResultTextArea = new JTextArea();
    private JTextPane approxMassTextPane = new JTextPane();
    private JTextPane approxContTextPane = new JTextPane();
    private JTextArea approxResultTextArea = new JTextArea();


    GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel changesPanel = new JPanel();
        changesPanel.setLayout(new BoxLayout(changesPanel, BoxLayout.X_AXIS));
        JPanel exactChangesPanel = new JPanel();
        exactChangesPanel.setLayout(new BoxLayout(exactChangesPanel, BoxLayout.Y_AXIS));
        exactChangesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
       //exactChangesPanel.setMaximumSize(new Dimension(200, 1000));
        JPanel approxChangesPanel = new JPanel();
        approxChangesPanel.setLayout(new BoxLayout(approxChangesPanel, BoxLayout.Y_AXIS));
        approxChangesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //approxChangesPanel.setMaximumSize(new Dimension(200, 1000));

        JTextArea weightTextArea = new JTextArea("0.3 0.2 0.4 0.4 0.5 0.3 0.3 0.4 ");
        weightTextArea.setMaximumSize(new Dimension(1000, 40));
        JButton button = new JButton("Calculate");

        exactResultTextArea.setEditable(false);
        exactOptContTextField.setEditable(false);
        exactVTextPane.setEditable(false);
        exactContTextPane.setEditable(false);

        approxMassTextPane.setEditable(false);
        approxContTextPane.setEditable(false);
        approxResultTextArea.setEditable(false);

        exactOptContTextField.setMaximumSize(new Dimension(600, 20));
        exactVTextPane.setMaximumSize(new Dimension(600, 20));
        exactContTextPane.setMaximumSize(new Dimension(600, 20));

        approxMassTextPane.setMaximumSize(new Dimension(600, 20));
        approxContTextPane.setMaximumSize(new Dimension(600, 20));

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

            Thread thread1 = new Thread(() -> {
                try {
                    ApproxCalculator.calculate(m);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            thread1.start();
        });

        panel.add(new JLabel("Вес предметов"));
        panel.add(weightTextArea);
        panel.add(button);
        panel.add(changesPanel);
        changesPanel.add(exactChangesPanel);
        changesPanel.add(approxChangesPanel);

        exactChangesPanel.add(new JLabel("OptCont"));
        exactChangesPanel.add(exactOptContTextField);
        exactChangesPanel.add(new JLabel("V"));
        exactChangesPanel.add(exactVTextPane);
        exactChangesPanel.add(new JLabel("Cont"));
        exactChangesPanel.add(exactContTextPane);
        exactChangesPanel.add(new JLabel("RESULT"));
        exactChangesPanel.add(exactResultTextArea);

        approxChangesPanel.add(new JLabel("Array"));
        approxChangesPanel.add(approxMassTextPane);
        approxChangesPanel.add(new JLabel("Containers"));
        approxChangesPanel.add(approxContTextPane);
        approxChangesPanel.add(new JLabel("RESULT"));
        approxChangesPanel.add(approxResultTextArea);
    }

    @Override
    public void stateChangedExact(double[] mass1, int[] mass2, int[] mass3) {
        SwingUtilities.invokeLater(() -> {
            exactVTextPane.setText(Arrays.toString(mass1));
            exactContTextPane.setText(Arrays.toString(mass2));
            exactOptContTextField.setText(Arrays.toString(mass3));
        });
    }

    @Override
    public void setResultExact(String res) {
        SwingUtilities.invokeLater(() -> {
            exactResultTextArea.setText(res);
        });
    }

    @Override
    public void stateChangedApprox(double[] mass1, double[] mass2, int i, int j) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc1 = (StyledDocument) approxMassTextPane.getDocument();
            StyledDocument doc2 = (StyledDocument) approxContTextPane.getDocument();
            try {
                String s1 = Arrays.toString(mass1);
                String s2 = Arrays.toString(mass2);
                doc1.remove(0, doc1.getLength());
                doc2.remove(0, doc2.getLength());
                doc1.insertString(0, s1, null);
                doc2.insertString(0, s2, null);

                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet as = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.RED);

                int start1 = 0;
                for (int k = 0; k < i; k++) {
                    start1 = s1.indexOf(',', start1 + 1);
                }
                ++start1;
                int len1 = s1.indexOf(',', start1) != -1 ? s1.indexOf(',', start1) - start1 : s1.length() - start1 - 1;

                int start2 = 0;
                for (int k = 0; k < j; k++) {
                    start2 = s2.indexOf(',', start2 + 1);
                }
                ++start2;
                int len2 = s2.indexOf(',', start2) != -1 ? s2.indexOf(',', start2) - start2 : s2.length() - start2 - 1;

                doc1.setCharacterAttributes(start1, len1, as, true);
                doc2.setCharacterAttributes(start2, len2, as, true);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            approxMassTextPane.setDocument(doc1);
            approxContTextPane.setDocument(doc2);
        });
    }

    @Override
    public void setResultApprox(String res) {
        System.err.println(res);
        SwingUtilities.invokeLater(() -> {
            approxResultTextArea.setText(res);
        });
    }
}
