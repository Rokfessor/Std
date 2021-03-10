import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Painter extends JPanel {
    Values values;
    Image image;
    Graphics gr;

    Painter() {
        try {
            image = ImageIO.read(new File("fon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        gr = g;
        paintCoords(g);
    }

    public void paint(Values values){
        this.values = values;
        try {
            image = ImageIO.read(new File("fon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        paintCoords(image.getGraphics());
        if (values != null)
            paintGraph(image.getGraphics());
        gr.drawImage(image, 0,0, null);
    }

    public void paintCoords(Graphics g){
        g.drawImage(image, 0, 0, null);
        g.setColor(new Color(248, 80, 68));
        g.drawLine(0, 600, 1280, 600); //x
        g.drawLine(620, 600, 620, 0); //y
        g.setColor(new Color(0, 255, 0));
    }

    public void paintGraph(Graphics g){
        g.setColor(new Color(247, 190, 71));
        double p = -3.14 * 2;
        boolean f = false;
        for (double i = -3.14 * 2; i <= 3.14 * 2; i += (1. / (values.size * 100))) {
            double x = i * values.size;
            double y = ICalc.calc(values.a, values.d, values.N, values.λ, i) * -values.size;
            g.drawLine((int) x + 620, (int) y + 600, (int) x + 620, (int) y + 600);
            if ((f && (y < p)) || (!f && (y > p))){
                f = !f;
                g.drawLine(((int) x + 620), 605, (int) x + 620, 595);
                int s = 3 + values.size / 100;
                g.setFont(new Font("TimesRoman", Font.PLAIN, s));
                g.drawString(String.format("%.3f",i), (int) x + 610, 610 + + values.size / 100);
            }
            p = y;
        }
    }
}
