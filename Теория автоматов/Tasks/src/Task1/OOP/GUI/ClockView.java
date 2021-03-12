package Task1.OOP.GUI;

import Task1.OOP.Model.Clock;
import Task1.OOP.Model.ClockListener;
import Task1.OOP.Model.ClockState;

import javax.swing.*;
import java.awt.*;

public class ClockView extends JLabel implements ClockListener {
    public ClockView(){
        super(String.format("%02d",Clock.getH()) + ":" + String.format("%02d",Clock.getM()));
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
    }

    @Override
    public void clockStateChanged(ClockState state) {
        switch (state) {
            case ON -> setForeground(Color.BLACK);
            case OFF -> setForeground(Color.LIGHT_GRAY);
            case ALARM_SET -> setForeground(Color.DARK_GRAY);
        }
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
