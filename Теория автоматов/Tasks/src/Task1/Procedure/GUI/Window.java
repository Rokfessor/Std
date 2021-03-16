package Task1.Procedure.GUI;

import Task1.Procedure.Model.ClockThread;
import Task1.Procedure.Model.Clock;
import Task1.Procedure.Model.ClockState;

import javax.swing.*;
import java.awt.*;

public class Window extends Container {
    public Window() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); //ЗАТЕСТИТЬ
        JButton aButton = new JButton("A");
        JButton hButton = new JButton("H");
        JButton mButton = new JButton("M");
        buttonPanel.add(aButton);
        buttonPanel.add(hButton);
        buttonPanel.add(mButton);

        ClockView clockView = new ClockView();
        clockView.setAlignmentX(Component.CENTER_ALIGNMENT);
        clockView.setForeground(Color.RED);

        add(clockView);
        add(buttonPanel);

        aButton.addActionListener(e -> {
            switch (Clock.state) {
                case ON -> {
                    Clock.state = ClockState.OFF;
                    clockView.setForeground(Color.RED);
                    Clock.M = 0;
                    Clock.H = 0;
                }
                case OFF -> {
                    Clock.state = ClockState.ALARM_SET;
                    clockView.setForeground(Color.BLUE);
                }
                case ALARM_SET -> {
                    Clock.state = ClockState.ON;
                    clockView.setForeground(Color.GREEN);
                    new ClockThread(clockView).start();
                }
            }
        });

        hButton.addActionListener(e -> {
            if (Clock.state == ClockState.ALARM_SET) {
                Clock.alarmH = (Clock.alarmH + 1) % 24;
                clockView.setValues(Clock.alarmH, Clock.alarmM);
            }
            if (Clock.state == ClockState.ON) {
                Clock.H = (Clock.H + 1) % 24;
                clockView.setValues(Clock.H, Clock.M);
            }
        });

        mButton.addActionListener(e -> {
            if (Clock.state == ClockState.ALARM_SET) {
                Clock.alarmM = (Clock.alarmM + 1) % 60;
                clockView.setValues(Clock.alarmH, Clock.alarmM);
            }
            if (Clock.state == ClockState.ON) {
                Clock.M = (Clock.M + 1) % 60;
                clockView.setValues(Clock.H, Clock.M);
            }
        });
    }
}
