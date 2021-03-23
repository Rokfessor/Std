import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;

public class GUI implements DMTListener {
    private static final JLabel imageLabel = new JLabel();
    private static final StyleContext sc = new StyleContext();
    private static final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    private static final Style st = sc.addStyle("color", null);
    private static final JTextPane textPane = new JTextPane();
    private static final ArrayList<Image> images = new ArrayList<>();
    private static DMT dmt;

    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        loadImages();

        try {
            doc.insertString(0, "--------", null);
            textPane.setDocument(doc);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        st.addAttribute(StyleConstants.Foreground, Color.red);
        JFrame frame = new JFrame();
        SwingUtilities.invokeLater(() -> frame.getContentPane().setBackground(Color.white));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField textArea = new JTextField();
        JButton button = new JButton("Посчитать");
        button.addActionListener(e -> {
            Thread thread = new Thread(() -> {
                try {
                    textPane.setText(Arrays.toString(DMT.startByString(textArea.getText())));
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            thread.start();
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setMaximumSize(new Dimension(1000, 20));
        Image img = images.get(0);

        img = img.getScaledInstance(400, 300, 0);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(JLabel.LEFT);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(textArea);
        panel.add(button);
        panel.add(textPane);
        panel.add(imageLabel);
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setVisible(true);
    }

    private static void loadImages() {
        int w = 400, h = 300;
        try {
            images.add(ImageIO.read(new File("resources/graph.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ1.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ2.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ2-2.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ3.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ3-2.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ4.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ4-2.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ5.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ5-2.png")).getScaledInstance(w, h, 0));
            images.add(ImageIO.read(new File("resources/toQ6.png")).getScaledInstance(w, h, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stateChanged(State state, int[] mass, int i) {
        switch (state) {
            case toQ1:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(1))));
                break;
            case toQ2:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(2))));
                break;
            case toQ2_2:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(3))));
                break;
            case toQ3:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(4))));
                break;
            case toQ3_2:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(5))));
                break;
            case toQ4:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(6))));
                break;
            case toQ4_2:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(7))));
                break;
            case toQ5:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(8))));
                break;
            case toQ5_2:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(9))));
                break;
            case toQ6:
                SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(images.get(10))));
                break;
        }
        SwingUtilities.invokeLater(() -> {
            try {
                StringBuilder sb = new StringBuilder();
                for (int j : mass)
                    sb.append(j);

                doc.remove(0, doc.getLength());
                doc.insertString(0, sb.toString(), null);
                doc.setCharacterAttributes(i + 1, 1, st, false);
                textPane.setDocument(doc);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });

    }
}
