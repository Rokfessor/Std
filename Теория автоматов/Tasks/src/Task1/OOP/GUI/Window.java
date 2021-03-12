package Task1.OOP.GUI;

import Task1.OOP.Model.Clock;

import javax.swing.*;
import java.awt.*;

public class Window extends Container {
    private Clock clock = new Clock();

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

        Clock clock = new Clock();
        clock.setClockListener(clockView);

        add(clockView);
        add(buttonPanel);

        aButton.addActionListener(clock);
        hButton.addActionListener(clock);
        mButton.addActionListener(clock);
    }
}
