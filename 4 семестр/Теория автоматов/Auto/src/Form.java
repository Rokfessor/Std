import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.Arrays;

public class Form extends JFrame {
    private JPanel panel;
    private JTextField rTF0;
    private JTextField yTF1;
    private JTextField yTF2;
    private JTextField yTF3;
    private JTextField yTF4;
    private JTextField yTF5;
    private JTextField yTF6;
    private JTextField yTF7;
    private JTextField a0TextField;
    private JTextField a0TextField10;
    private JTextField a1TextField4;
    private JTextField a1TextField5;
    private JTextField a1TextField8;
    private JTextField a0TextField8;
    private JTextField a1TextField9;
    private JTextField a1TextField3;
    private JTextField SDNFTF;
    private JTextField SDNFUTF;
    private JTextField a0TextField2;
    private JTextField a0TextField1;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField a1TextField7;
    private JTextField a1TextField6;
    private JTextField a1TextField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField7;
    private JPanel paintPanel;

    public Form() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setContentPane(panel);

        /*SwingUtilities.invokeLater(() -> {
            scrollPane.removeAll();
            scrollPane.add(new Painter(null));

            repaint();
            revalidate();
        });*/

        JTextField[] tfs = {rTF0, yTF1, yTF2, yTF3, yTF4, yTF5, yTF6, yTF7};

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (e.getDocument().getLength() > 1) {
                    SwingUtilities.invokeLater(() -> {
                        try {
                            e.getDocument().remove(1, e.getLength());
                        } catch (BadLocationException ignored) {
                        }
                    });
                }

                try {
                    if (!e.getDocument().getText(0, 1).equals("0") && !e.getDocument().getText(0, 1).equals("1")) {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                e.getDocument().remove(0, 1);
                            } catch (BadLocationException badLocationException) {
                                badLocationException.printStackTrace();
                            }
                        });
                    }
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }

                int[] m = new int[tfs.length];
                try {
                    for (int i = 0; i < m.length; i++) {
                        String s = tfs[i].getText();
                        int a = Integer.parseInt(s);
                        if (a < 2)
                            m[i] = a;
                    }
                    drawData(Controller.convertAndCalculate(m));
                } catch (NumberFormatException ignored) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        };

        rTF0.getDocument().addDocumentListener(listener);
        yTF1.getDocument().addDocumentListener(listener);
        yTF2.getDocument().addDocumentListener(listener);
        yTF3.getDocument().addDocumentListener(listener);
        yTF4.getDocument().addDocumentListener(listener);
        yTF5.getDocument().addDocumentListener(listener);
        yTF6.getDocument().addDocumentListener(listener);
        yTF7.getDocument().addDocumentListener(listener);
    }

    private void drawData(DataHolder dh) {
        StringBuilder sbSDNF = new StringBuilder();
        for (int i = 0; i < dh.getSDNF().size(); i++){
            if (i != dh.getSDNF().size() - 1)
                sbSDNF.append(Utils.performSDNF(dh.getSDNF().get(i))).append(" ∨ ");
            else
                sbSDNF.append(Utils.performSDNF(dh.getSDNF().get(i)));
        }

        StringBuilder sbSDNFU = new StringBuilder();
        for (int i = 0; i < dh.getSDNFU().size(); i++){
            if (i != dh.getSDNFU().size() - 1)
                sbSDNFU.append(Utils.performSDNF(dh.getSDNFU().get(i))).append(" ∨ ");
            else
                sbSDNFU.append(Utils.performSDNF(dh.getSDNFU().get(i)));
        }

        SwingUtilities.invokeLater(() -> {
            SDNFTF.setText(sbSDNF.toString());
            SDNFUTF.setText(sbSDNFU.toString());

            paintPanel.removeAll();
            paintPanel.add(new Painter(dh.getSDNFU(),sbSDNFU.toString()));
            panel.revalidate();
            panel.repaint();
        });
    }

    private void createUIComponents() {
        ArrayList<String> bb = new ArrayList<>(Arrays.asList("123", "423", "234", "234"));
        paintPanel = new JPanel();
        Painter p = new Painter(bb, "");
        paintPanel.add(p);
    }
}
