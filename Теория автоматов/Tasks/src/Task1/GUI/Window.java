package Task1.GUI;

import Task1.Model.Clock;
import Task1.Model.ClockState;

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

        Clock.setClockListener(clockView);

        add(clockView);
        add(buttonPanel);

        aButton.addActionListener(e -> {
            if (Clock.state == ClockState.OFF) {
                clockView.setForeground(Color.GRAY);
                Clock.setAlarm();
            } else if (Clock.state == ClockState.ALARM_SET) {
                clockView.setForeground(Color.BLACK);
                Clock.turnOn();
            } else if (Clock.state == ClockState.ON) {
                Clock.turnOff();
            }
        });

        hButton.addActionListener(e -> {
            if (Clock.state == ClockState.ALARM_SET) {
                Clock.setAlarmH((Clock.getAlarmH() + 1) % 24);
            }
        });

        mButton.addActionListener(e -> {
            if (Clock.state == ClockState.ALARM_SET) {
                Clock.setAlarmM((Clock.getAlarmM() + 1) % 60);
            }
        });
    }
}
