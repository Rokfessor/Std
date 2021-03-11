package Task1.Model;

public class ClockThread extends Thread {
    @Override
    public void run() {
        while (Clock.state == ClockState.ON) {
            try {
                sleep(600);
                Clock.setM((Clock.getM() + 1) % 60);
                if (Clock.getM() == 0)
                    Clock.setH((Clock.getH() + 1) % 24);

                if (Clock.getM() == Clock.getAlarmM() && Clock.getH() == Clock.getAlarmH())
                    Clock.awake();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
