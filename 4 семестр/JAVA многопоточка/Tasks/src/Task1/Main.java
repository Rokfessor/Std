package Task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++){
            long t = System.currentTimeMillis();
            Counter counter = Find.findWithThread(8, (int) Math.pow(2, i));
            System.err.println("Thread: size-" + 8 + " count-" + (int)Math.pow(2, i) + " time-" + (System.currentTimeMillis() - t) / 1000.);
            System.err.println(counter + "\n");
            Counter.Coun.reset();

            t = System.currentTimeMillis();
            counter = Find.findWithRunnable(8, (int) Math.pow(2, i));
            System.err.println("Runnable: size-" + 8 + " count-" + (int)Math.pow(2, i) + " time-" + (System.currentTimeMillis() - t) / 1000.);
            System.err.println(counter + "\n");
            Counter.Coun.reset();
        }
        System.err.println();

        for (int i = 0; i < 4; i++){
            long t = System.currentTimeMillis();
            Counter counter = Find.findWithThread(10, (int) Math.pow(2, i));
            System.err.println("Thread: size-" + 10 + " count-" + (int)Math.pow(2, i) + " time-" + (System.currentTimeMillis() - t) / 1000.);
            System.err.println(counter + "\n");
            Counter.Coun.reset();

            t = System.currentTimeMillis();
            counter = Find.findWithRunnable(10, (int) Math.pow(2, i));
            System.err.println("Runnable: size-" + 10 + " count-" + (int)Math.pow(2, i) + " time-" + (System.currentTimeMillis() - t) / 1000.);
            System.err.println(counter + "\n");
            Counter.Coun.reset();
        }
    }
}
