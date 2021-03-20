package Task1.Procedure.Model;

import Task1.Procedure.GUI.ClockView;

import javax.swing.*;

public class ClockThread extends Thread {
    ClockView clockView;

    public ClockThread(ClockView clockView){
        this.clockView = clockView;
    }

    @Override
    public void run() {
        while (Clock.state == ClockState.ON) {
            try {
                sleep(600);
                Clock.M = (Clock.M + 1) % 60;
                if (Clock.M == 0)
                    Clock.H = (Clock.H + 1) % 24;
                clockView.setValues(Clock.H, Clock.M);

                if (Clock.M == Clock.alarmM && Clock.H == Clock.alarmH)
                    JOptionPane.showMessageDialog(null, "Wake Up!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
