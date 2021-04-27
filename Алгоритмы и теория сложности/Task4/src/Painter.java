import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Painter extends JPanel {
    private DataHolder dataHolder;
    final int height = 20;
    final int width = 70;
    final int startY = 30;
    final int startX = 10;

    public Painter(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    public void paint(Graphics g) {
        if (dataHolder != null) {
            int x = startX;
            int yUp = startY;
            int yDown = yUp + height;

            int xShiftTitle = x;
            int yTitle = startY - height / 2;
            g.drawString("try(i)", x, yTitle);

            for (int j = 1; j <= dataHolder.steps.get(0).N.length; j++) {
                xShiftTitle += width;
                g.drawLine(xShiftTitle, yUp, xShiftTitle, yDown);
                g.drawString("№" + j, xShiftTitle, yTitle);
            }
            xShiftTitle += width;

            g.drawString("Сумм. вес", xShiftTitle, yTitle);
            xShiftTitle += width;

            g.drawString("Сумм. стоим.", xShiftTitle, yTitle);
            xShiftTitle += width;

            g.drawString("Возм. стоим.", xShiftTitle, yTitle);
            xShiftTitle += width;

            g.drawString("Опт. стоим.", xShiftTitle, yTitle);
            xShiftTitle += width;

            g.drawString("Примечания", xShiftTitle, yTitle);

            for (int i = 0; i < dataHolder.steps.size(); i++) {
                g.drawLine(x, yUp, xShiftTitle, yUp);
                g.drawLine(x, yDown, xShiftTitle, yDown);
                g.drawLine(x, yUp, x, yDown);

                int xShift = x + width;

                g.drawLine(xShift, yUp, xShift, yDown); // try(i)
                g.drawString(dataHolder.steps.get(i).tryI, xShift - width + 2, yUp + height / 2);
                xShift += width;

                for (int j = 0; j < dataHolder.steps.get(i).N.length; j++) { // №
                    g.drawLine(xShift, yUp, xShift, yDown);
                    g.drawString(dataHolder.steps.get(i).N[j], xShift - width + 2, yUp + height / 2);
                    xShift += width;
                }

                g.drawLine(xShift, yUp, xShift, yDown); // Суммарный вес
                g.drawString(String.valueOf(dataHolder.steps.get(i).sumV), xShift - width + 2, yUp + height / 2);
                xShift += width;

                g.drawLine(xShift, yUp, xShift, yDown); // Суммарная стоимость
                g.drawString(String.valueOf(dataHolder.steps.get(i).sumCost), xShift - width + 2, yUp + height / 2);
                xShift += width;

                g.drawLine(xShift, yUp, xShift, yDown); // Возможная стоимость
                g.drawString(String.valueOf(dataHolder.steps.get(i).vozmCost), xShift - width + 2, yUp + height / 2);
                xShift += width;

                g.drawLine(xShift, yUp, xShift, yDown); // Оптилаьная стоимость
                g.drawString(String.valueOf(dataHolder.steps.get(i).optCost), xShift - width + 2, yUp + height / 2);

                g.drawString(dataHolder.steps.get(i).note, xShift + 2, yUp + height / 2); // Примечание

                yUp += height;
                yDown = yUp + height;
            }

            g.drawLine(x, startY, x, yUp);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(700, 700);
    }
}
