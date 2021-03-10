import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements PanelListener {
    final Box box = new Box(BoxLayout.Y_AXIS);
    ChoosePanel choosePanel;
    Painter painter = new Painter();

    public Window() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graphs");
        getContentPane().add(box);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(1280, 720));
        setPreferredSize(new Dimension(1280, 720));
        setLocation(((screen.width / 2) - (getWidth() / 2)) / 2, ((screen.height / 2) - (getHeight() / 2)) / 2);
        initView();
        pack();
        setVisible(true);
        variableChange(new Values(0.2, 2.5, 3, 600, 500));
    }

    private void initView(){
        box.add(painter);
        choosePanel = new ChoosePanel(getWidth());
        choosePanel.addListener(this);
        box.add(choosePanel);
    }

    @Override
    public void variableChange(Values values) {
        if (values != null) {
            painter.paint(values);
            repaint();
        }
    }
}