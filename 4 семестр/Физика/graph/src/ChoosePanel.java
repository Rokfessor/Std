import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ChoosePanel extends JPanel {
    JTextField a = new JTextField("0.2"),
            d = new JTextField("2.5"),
            N = new JTextField("3"),
            λ = new JTextField("600"),
            size = new JTextField("500");
    PanelListener listener;

    ChoosePanel(int width) {
        initView(width);
        DocumentListener lis = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.variableChange(setValues());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.variableChange(setValues());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.variableChange(setValues());
            }
        };
        a.getDocument().addDocumentListener(lis);
        d.getDocument().addDocumentListener(lis);
        N.getDocument().addDocumentListener(lis);
        λ.getDocument().addDocumentListener(lis);
        size.getDocument().addDocumentListener(lis);
    }

    void initView(int width) {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setMinimumSize(new Dimension(width, 25));
        setMaximumSize(new Dimension(width, 25));
        setVisible(true);
        Dimension di = new Dimension(50, 22);
        a.setSize(di);
        a.setPreferredSize(di);
        d.setSize(di);
        d.setPreferredSize(di);
        N.setSize(di);
        N.setPreferredSize(di);
        λ.setSize(di);
        λ.setPreferredSize(di);
        size.setSize(di);
        size.setPreferredSize(di);
        add(new JLabel("a"));
        add(a);
        add(new JLabel("d"));
        add(d);
        add(new JLabel("N"));
        add(N);
        add(new JLabel("λ"));
        add(λ);
        add(new JLabel("size"));
        add(size);
    }

    Values setValues() {
        Values values = new Values();
        try {
            values.a = Double.parseDouble(a.getText());
            values.d = Double.parseDouble(d.getText());
            values.N = Double.parseDouble(N.getText());
            values.λ = Double.parseDouble(λ.getText());
            values.size = Integer.parseInt(size.getText());

        } catch (NumberFormatException ignored) {
            values = null;
        }

        return values;
    }

    void addListener(PanelListener listener) {
        this.listener = listener;
    }
}
