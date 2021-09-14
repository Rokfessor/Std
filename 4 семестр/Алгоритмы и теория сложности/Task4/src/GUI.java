import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class GUI extends JFrame {
    public GUI(){
        JPanel panel = (JPanel)getContentPane();

        JLabel vesLabel = new JLabel("Максимальный вес");
        JTextField vesTextField = new JTextField("100");
        vesTextField.setMaximumSize(new Dimension(300, 25));
        JLabel vLabel = new JLabel("Вес объектов");
        JTextField vTextField = new JTextField("25 35 80");
        vTextField.setMaximumSize(new Dimension(300, 25));
        JLabel stLabel = new JLabel("Стоимость объектов");
        JTextField stTextField = new JTextField("30 25 60");
        stTextField.setMaximumSize(new Dimension(300, 25));
        JButton button = new JButton("Рассчитать");
        vTextField.setMaximumSize(new Dimension(300, 25));
        JLabel result = new JLabel("Результат: Общий вес: Общая стоимтость:");
        JLabel step = new JLabel("Кол-во шагов: ");
        JLabel massApprox =  new JLabel("Сформированный массив: ");
        JLabel approxStep = new JLabel("Кол-во шагов: ");
        JLabel approxResult = new JLabel("Результат: Общий вес: Общая стоимтость:");

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(new EmptyBorder(10,10,10,10));

        JPanel paintPanel = new JPanel();

        button.addActionListener(e -> {
            DataHolder dataHolder = Calculator.calculate(Integer.parseInt(vesTextField.getText()), Utils.performData(vTextField.getText(), stTextField.getText()));
            int st = 0, v = 0;
            for (Obj obj : dataHolder.result){
                st += obj.st;
                v += obj.v;
            }
            step.setText("Кол-во шагов: " + dataHolder.step);
            result.setText("Результат: " + Arrays.toString(dataHolder.result) + " Общий вес: " + v + " Общая стоимтость: " + st);
            SwingUtilities.invokeLater(() -> {
                paintPanel.removeAll();
                paintPanel.add(new Painter(dataHolder));
                panel.revalidate();
                panel.repaint();
            });


            ApproxDataHolder approxDataHolder = ApproxCalculator.calculate(Integer.parseInt(vesTextField.getText()), Utils.performData(vTextField.getText(), stTextField.getText()));
            int stA = 0, vA = 0;
            for (Obj obj : approxDataHolder.res){
                stA += obj.st;
                vA += obj.v;
            }
            approxStep.setText("Кол-во шагов: " + approxDataHolder.step);
            massApprox.setText("Сформированный массив: " + Arrays.toString(approxDataHolder.mass));
            approxResult.setText("Результат: " + Arrays.toString(approxDataHolder.res) + " Общий вес: " + vA + " Общая стоимтость: " + stA);
        });

        panel.add(vesLabel);
        panel.add(vesTextField);
        panel.add(vLabel);
        panel.add(vTextField);

        panel.add(stLabel);
        panel.add(stTextField);
        panel.add(button);
        panel.add(new JLabel("========Точный алгоритм========="));
        panel.add(step);
        panel.add(result);
        panel.add(paintPanel);
        panel.add(new JLabel("========Приближенный алгоритм========="));
        panel.add(massApprox);
        panel.add(approxStep);
        panel.add(approxResult);
    }
}
