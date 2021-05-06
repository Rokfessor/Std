package Task6;

import java.text.SimpleDateFormat;

public class Timer {
    static long startTime;
    static long coeff = 24 * 60L;
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM-HH:mm");

    public static String getCurrentTime() {
        return format.format((System.currentTimeMillis() - Timer.startTime) * Timer.coeff);
    }

    public static long calcOtnHours(double hours) {
        return (long) ((hours * 60 * 60 * 1000) / coeff);
    }

    public static long calcOtnMinutes(double minutes) {
        return (long) ((minutes  * 60 * 1000) / coeff);
    }
}
