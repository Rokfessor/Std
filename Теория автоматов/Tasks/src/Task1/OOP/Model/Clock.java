package Task1.OOP.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock implements ActionListener {
    static ClockListener clockListener;
    public static ClockState state = ClockState.OFF;
    private static int H = 0, M = 0;
    private static int alarmH = 0, alarmM = 0;

    public static void turnOn() {
        reset();
        Clock.state = ClockState.ON;
        new ClockThread().start();
    }

    public static void turnOff() {
        Clock.state = ClockState.OFF;
        reset();
    }

    public static void reset() {
        setH(0);
        setM(0);
    }

    public static int getH() {
        return H;
    }

    public static int getM() {
        return M;
    }

    public static void setH(int h) {
        H = h;
        clockListener.setClockValues();
    }

    public static void setM(int m) {
        M = m;
        clockListener.setClockValues();
    }

    public void setClockListener(ClockListener listener) {
        clockListener = listener;
    }

    public static int getAlarmH() {
        return alarmH;
    }

    public static int getAlarmM() {
        return alarmM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "A" -> aButtonAction();
            case "H" -> alarmButtonsAction("H");
            case "M" -> alarmButtonsAction("M");
        }
    }

    private void aButtonAction() {
        switch (state) {
            case OFF -> Clock.state = ClockState.ALARM_SET;
            case ALARM_SET -> Clock.turnOn();
            case ON -> Clock.turnOff();
        }
        clockListener.clockStateChanged(state);
    }

    private void alarmButtonsAction(String type) {
        if (Clock.state == ClockState.ALARM_SET) {
            System.err.println(type);
            if (type.equals("H"))
                alarmH = (alarmH + 1) % 24;
            else
                alarmM = (alarmM + 1) % 60;
            clockListener.setAlarmValues();
        }
    }
}