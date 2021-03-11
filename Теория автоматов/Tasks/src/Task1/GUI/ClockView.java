package Task1.GUI;

import Task1.Model.Clock;
import Task1.Model.ClockListener;

import javax.swing.*;
import java.awt.*;

public class ClockView extends JLabel implements ClockListener {
    public ClockView(){
        super(String.format("%02d",Clock.getH()) + ":" + String.format("%02d",Clock.getM()));
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
    }

    @Override
    public void setClockValues() {
        setText(String.format("%02d",Clock.getH()) + ":" + String.format("%02d",Clock.getM()));
    }

    @Override
    public void setAlarmValues() {
        setText(String.format("%02d",Clock.getAlarmH()) + ":" + String.format("%02d",Clock.getAlarmM()));
    }
}
