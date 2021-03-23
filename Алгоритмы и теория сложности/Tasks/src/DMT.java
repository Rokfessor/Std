import java.util.Arrays;

public class DMT {
    private static DMTListener listener;
    private static int l = 1000;
    public static void setDMTListener(DMTListener listener){
        DMT.listener = listener;
    }

    public static int[] startByString(String line) throws InterruptedException {
        int[] mass = new int[line.length()];
        for (int i = 0; i < mass.length; i++){
            mass[i] = line.charAt(i) - '0';
        }
        return start(mass);
    }

    public static int[] start(int[] mass) throws InterruptedException {
        listener.stateChanged(State.toQ1, mass, 0);
        Thread.sleep(l);
        return q1(mass, 0);
    }

    private static int[] q1(int[] mass, int i) throws InterruptedException {
        System.err.print("q1 -> ");
        if (mass[i] == 1) {
            mass[i] = 0;
            i--;
            listener.stateChanged(State.toQ2, mass, i);
            Thread.sleep(l);
            return q2(mass, i);
        }
        return null;
    }

    private static int[] q2(int[] mass, int i) throws InterruptedException {
        System.err.print("q2 ->");
        listener.stateChanged(State.toQ2_2, mass, i);
        Thread.sleep(l);

        i = 0;
        mass = prepareMass(mass);
        mass[i] = 1;
        listener.stateChanged(State.toQ3, mass, i);
        Thread.sleep(l);
        return q3(mass, i);
    }

    private static int[] q3(int[] mass, int i) throws InterruptedException {
        System.err.print("q3 ->");
        for (; i < mass.length; i++) {
            listener.stateChanged(State.toQ3_2, mass, i);
            Thread.sleep(l);
            if (mass[i] != 1)
                break;
        }
        listener.stateChanged(State.toQ4, mass, i);
        Thread.sleep(l);
        return q4(mass, i);
    }

    private static int[] q4(int[] mass, int i) throws InterruptedException {
        System.err.print("q4 ->");
        boolean f = true;
        for (; i < mass.length; i++) {
            listener.stateChanged(State.toQ4_2, mass, i);
            Thread.sleep(l);
            if (mass[i] != 0) {
                listener.stateChanged(State.toQ1, mass, i);
                Thread.sleep(l);
                return q1(mass, i);
            }
        }
        listener.stateChanged(State.toQ5, mass, i);
        Thread.sleep(l);
        return q5(mass, i);
    }

    private static int[] q5(int[] mass, int i) throws InterruptedException {
        System.err.print("q5 ->");
        for (int j = mass.length - 1; j >= 0; j--) {
            listener.stateChanged(State.toQ5_2, mass, i);
            Thread.sleep(l);
            mass[j] = 1;
        }
        listener.stateChanged(State.toQ6, mass, i);
        Thread.sleep(l);
        return mass;
    }

    private static int[] prepareMass(int[] mass) {
        mass = Arrays.copyOf(mass, mass.length + 1);
        for (int i = mass.length - 1; i > 0; i--) {
            mass[i] = mass[i - 1];
        }
        mass[0] = 0;

        return mass;
    }
}
