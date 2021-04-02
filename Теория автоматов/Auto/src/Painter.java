import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Painter extends JPanel {
    private static final int DIM_WIDTH = 800;
    private static final int DIM_HEIGHT = 500;
    private ArrayList<String> data;
    String res;
    private int x = 200, y = 50, w = 55, h = 55, wNOT = w / 3, hNOT = h / 3;
    // x, y - начальные значения, h,w - размеры блоков И, wNOT,hNOT - размеры блоков НЕ
    public Painter(ArrayList<String> data, String res) {
        this.data = data;
        this.res = res;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        y = 50;
        g.setColor(Color.BLACK);
        if (data != null) {
            int y1 = y;
            int[] xN = {1, 11, 21}; // A B C
            g.drawLine(xN[0],1,1,800);
            g.drawLine(xN[1],1,11,800);
            g.drawLine(xN[2],1,21,800);
            g.setColor(Color.RED);
            g.drawString("A", xN[0] + 1, 10);
            g.drawString("B", xN[1] + 1, 10);
            g.drawString("C", xN[2] + 1, 10);
            g.setColor(Color.BLACK);

            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                g.drawRect(x, y1, w, h);
                g.drawString("И", x + w/2 - 5, y1 + h/2);
                g.setColor(Color.RED);
                g.drawString(Utils.performSDNF(s), x + w + 5, y1 + w/2 - 5);
                g.setColor(Color.BLACK);
                g.drawLine(x + w,y1 + w/2, x + w + 75 ,y1 + w/2);

                int yIn = y1;
                for (int j = 0; j < 3; j++) {
                    if (s.charAt(j) == '-') {
                        yIn += 20;
                        continue;
                    }
                    if (s.charAt(j) == '1'){
                        g.setColor(Color.RED);
                        g.drawString(String.valueOf((char)(j + 65)), x - 160, yIn + 8);
                        g.setColor(Color.BLACK);
                        g.drawLine(xN[j],yIn + 9, x ,yIn + 9);
                        yIn += 20;
                        continue;
                    }
                    if (s.charAt(j) == '0'){
                        g.drawRect(x - 75, yIn, wNOT, hNOT);
                        g.drawString("НЕ", x - 72, yIn + 15);
                        g.setColor(Color.RED);
                        g.drawString("¬" + (char) (j + 65), x - 55, yIn + 7);
                        g.setColor(Color.BLACK);
                        g.drawLine(x - 57,yIn + 9, x ,yIn + 9);
                        g.setColor(Color.RED);
                        g.drawString(String.valueOf((char)(j + 65)), x - 160, yIn + 8);
                        g.setColor(Color.BLACK);
                        g.drawLine(xN[j],yIn + 9, x - 75 ,yIn + 9);
                        yIn += 20;
                    }
                }
                y1 += 75;
            }

            g.drawLine(x + w + 75,y + w/2,x + w + 75,y1 - w + 6);
            int temY = ((y1 - w + 6) + (y + w/2)) / 2;
            g.drawLine(x + w + 75, temY, x + w + 125, temY);
            g.drawRect(x + w + 125, temY - w/2, w, h);
            g.drawString("ИЛИ",x + w + 125 + w/2 - 15, temY);
            g.setColor(Color.RED);
            g.drawString(res, x + w + 130 + w, temY - 4);
            g.setColor(Color.BLACK);
            g.drawLine(x + w + 125 + w, temY, x + w + 175 + w, temY);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DIM_WIDTH, DIM_HEIGHT);
    }
}

/*public class Painter extends JPanel {
    private ArrayList<String> data;
    private int x = 50, y = 50, w = 50, h = 50;

    public Painter(ArrayList<String> data) {
        System.err.println("hello");
        this.data = data;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.err.println("d");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                //g2.drawRect(x, y, w, h);
                g2.drawString("И", 0,0);
                y += 75;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        System.err.println("==");
        return new Dimension(800, 800);
    }

    private void drawBlock(String data, Graphics g) {
        g.drawRect(x, y, w, h);

        g.drawString("И", (x + x / 2), (y - h / 2));
        y += 75;
    }

}*/
