package GUI;

import Task1.CompoundCalc;
import Task1.SimpleCalc;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GUI {
    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(mainPanel);
        frame.setPreferredSize(new Dimension(450, 400));
        frame.setMinimumSize(new Dimension(450, 400));
        frame.setResizable(false);
        frame.setVisible(true);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        setWidgets(mainPanel);
    }

    private static void setWidgets(JPanel mainPanel){
        JPanel TFPanel = new JPanel();
        TFPanel.setLayout(new BoxLayout(TFPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JScrollPane tablePane = new JScrollPane();

        JTable table = new JTable(new Object[][]{}, new String[] {"Наименование", "Ед.измерения",
                "Количество"});

        JTextField iTF = new JTextField();
        JTextField PVTF = new JTextField();
        JTextField FVTF = new JTextField();
        JTextField nTF = new JTextField();

        JLabel iLabel = new JLabel("Процент");
        JLabel PVLabel = new JLabel("Сумма вклада");
        JLabel nLabel = new JLabel("Количество лет");
        JLabel FVLabel = new JLabel("Сумма");

        JButton calcSimpleButton = new JButton("Посчитать простые проценты");
        calcSimpleButton.addActionListener(e -> {
            try {
                if (FVTF.getText().equals("")) {
                    FVTF.setText(Double.toString(SimpleCalc.calcFV(
                            Double.parseDouble(PVTF.getText()),
                            Double.parseDouble(iTF.getText()),
                            Double.parseDouble(nTF.getText()))));
                    return;
                }

                if (PVTF.getText().equals("")) {
                    PVTF.setText(Double.toString(SimpleCalc.calcPV(
                            Double.parseDouble(FVTF.getText()),
                            Double.parseDouble(iTF.getText()),
                            Double.parseDouble(nTF.getText()))));
                    return;
                }

                if (iTF.getText().equals("")) {
                    iTF.setText(Double.toString(SimpleCalc.calcI(
                            Double.parseDouble(FVTF.getText()),
                            Double.parseDouble(PVTF.getText()),
                            Double.parseDouble(nTF.getText()))));
                    return;
                }

                if (nTF.getText().equals("")) {
                    nTF.setText(Double.toString(SimpleCalc.calcN(
                            Double.parseDouble(FVTF.getText()),
                            Double.parseDouble(PVTF.getText()),
                            Double.parseDouble(iTF.getText()))));
                    return;
                }
                throw new IOException("Incorrect input");

            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        JButton calcCompButton = new JButton("Посчитать сложные проценты");
        calcCompButton.addActionListener(e -> {
            try {
                if (FVTF.getText().equals("")) {
                    FVTF.setText(Double.toString(CompoundCalc.calcFV(
                            Double.parseDouble(PVTF.getText()),
                            Double.parseDouble(iTF.getText()),
                            Double.parseDouble(nTF.getText()))));
                    return;
                }

                if (PVTF.getText().equals("")) {
                    PVTF.setText(Double.toString(CompoundCalc.calcPV(
                            Double.parseDouble(FVTF.getText()),
                            Double.parseDouble(iTF.getText()),
                            Double.parseDouble(nTF.getText()))));
                    return;
                }

                if (iTF.getText().equals("")) {
                    iTF.setText(Double.toString(CompoundCalc.calcI(
                            Double.parseDouble(FVTF.getText()),
                            Double.parseDouble(PVTF.getText()),
                            Double.parseDouble(nTF.getText()))));
                    return;
                }

                if (nTF.getText().equals("")) {
                    nTF.setText(Double.toString(CompoundCalc.calcN(
                            Double.parseDouble(FVTF.getText()),
                            Double.parseDouble(PVTF.getText()),
                            Double.parseDouble(iTF.getText()))));
                    return;
                }
                throw new IOException("Incorrect input");

            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        calcSimpleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(TFPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(tablePane);

        tablePane.add(table);
        TFPanel.add(FVLabel);
        TFPanel.add(FVTF);
        TFPanel.add(iLabel);
        TFPanel.add(iTF);
        TFPanel.add(PVLabel);
        TFPanel.add(PVTF);
        TFPanel.add(nLabel);
        TFPanel.add(nTF);
        buttonPanel.add(calcSimpleButton);
        buttonPanel.add(calcCompButton);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(GUI::createGUI);
    }
}