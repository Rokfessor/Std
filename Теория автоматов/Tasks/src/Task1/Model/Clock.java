package Task1.Model;

import javax.swing.*;

public class Clock {
    static ClockListener clockListener;
    public static ClockState state = ClockState.OFF;
    private static int H = 0, M = 0;
    private static int alarmH = 0, alarmM = 0;

    public static void turnOn() {
        Clock.state = ClockState.ON;
        ClockThread clockThread = new ClockThread();
        clockThread.start();
    }

    public static void awake(){
        JOptionPane.showMessageDialog(null, "Wake Up!");
    }

    public static void reset(){
        setH(0);
        setM(0);
    }

    public static void turnOff() {
        Clock.state = ClockState.OFF;
        reset();
    }

    public static int getH() {
        return H;
    }

    public static int getM() {
        return M;
    }

    public static void setClockListener(ClockListener listener) {
        clockListener = listener;
    }

    public static void setH(int h) {
        H = h;
        clockListener.setClockValues();
    }

    public static void setM(int m) {
        M = m;
        clockListener.setClockValues();
    }

    public static int getAlarmH() {
        return alarmH;
    }

    public static void setAlarmH(int alarmH) {
        Clock.alarmH = alarmH;
        clockListener.setAlarmValues();
    }

    public static int getAlarmM() {
        return alarmM;
    }

    public static void setAlarmM(int alarmM) {
        Clock.alarmM = alarmM;
        clockListener.setAlarmValues();
    }

    public static void setAlarm() {
        Clock.state = ClockState.ALARM_SET;
    }
}
