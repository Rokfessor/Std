package Task1;

import javax.swing.*;
import java.awt.*;

public class Painter extends JComponent {
    Painter(int x, int y){
        setMinimumSize(new Dimension(x, y));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawOval(50,50,50,50);//q1
        graphics.drawString("q1", 70,75); //string
        graphics.drawLine(75,100,75,200);//q1->q2
        graphics.drawLine(75,200,65,190);//стрелка q1->q2
        graphics.drawLine(75,200,85,190);//стрелка q1->q2

        graphics.drawOval(50,200,50,50);//q2
        graphics.drawString("q2", 70,225); //string
        graphics.drawLine(100, 225,200,225);//q2->q3
        graphics.drawLine(200,225,190,215);//стрелка q2->q3
        graphics.drawLine(200,225,190,235);//стрелка q2->q3


        graphics.drawOval(200,50,50,50);//q3
        graphics.drawString("q3", 220,75); //string
        graphics.drawLine(225,100,225,200);//q3->q4
        graphics.drawLine(225,100,215,110);//стрелка q3->q4
        graphics.drawLine(225,100,235,110);//стрелка q3->q4

        graphics.drawOval(200,200,50,50);//q4
        graphics.drawString("q4", 220,225); //string
        graphics.drawLine(100, 75,200,75);//q4->q1
        graphics.drawLine(100,75, 110,85);//стрелка q4->q1
        graphics.drawLine(100,75, 110,65);//стрелка q4->q1
        graphics.drawLine(250, 75,350,75);//q4->q5
        graphics.drawLine(350,75,340,65);//стрелка q4->q5
        graphics.drawLine(350,75,340,85);//стрелка q4->q5

        graphics.drawOval(350,50,50,50);//q5
        graphics.drawString("q5", 370,75); //string
        graphics.drawLine(375,100,375,200);//q5->q6
        graphics.drawLine(375,200, 365,190);
        graphics.drawLine(375,200, 385,190);

        graphics.drawOval(350,200,50,50);//q6
        graphics.drawOval(360,210,30,30);//q6
        graphics.drawString("q6", 370,225); //string
    }
}
