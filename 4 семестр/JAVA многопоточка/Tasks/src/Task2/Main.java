package Task2;

public class Main {
    public static void main(String[] args) {
        int gaps = 100000000;
        System.err.println("===RECT===");
        try {
            for (int i = 1; i <= 3; i++) {
                long t = System.currentTimeMillis();
                double res = CalcWithThread.calcRect(Math.PI / 6L, Math.PI / 3L, (int) Math.pow(2, i), gaps);
                System.err.println("Thread: gaps-" + gaps + " threads count-" + (int) Math.pow(2, i) + " time-" +
                        (System.currentTimeMillis() - t) / 1000.);
                System.err.println(res);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("\n===TRAPEZ===");
        try {
            for (int i = 1; i <= 3; i++) {
                long t = System.currentTimeMillis();
                double res = CalcWithThread.calcTrapez(Math.PI / 6L, Math.PI / 3L, (int) Math.pow(2, i), gaps);
                System.err.println("Thread: gaps-" + gaps + " threads count-" + (int) Math.pow(2, i) + " time-" +
                        (System.currentTimeMillis() - t) / 1000.);
                System.err.println(res);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("\n===SIMPS===");
        try {
            for (int i = 1; i <= 3; i++) {
                long t = System.currentTimeMillis();
                double res = CalcWithThread.calcSimps(Math.PI / 6L, Math.PI / 3L, (int) Math.pow(2, i), gaps);
                System.err.println("Thread: gaps-" + gaps + " threads count-" + (int) Math.pow(2, i) + " time-" +
                        (System.currentTimeMillis() - t) / 1000.);
                System.err.println(res);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
