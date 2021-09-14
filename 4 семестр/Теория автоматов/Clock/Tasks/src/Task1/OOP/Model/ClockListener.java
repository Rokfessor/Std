package Task1.OOP.Model;

public interface ClockListener {
    void clockStateChanged(ClockState state);
    void setClockValues();
    void setAlarmValues();
}
