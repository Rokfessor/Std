import java.util.Arrays;

public class ApproxCalculator {
    private static CalcListener listener;
    private final static long sleep = 500;
    private static long step;

    public static void setListener(CalcListener listener) {
        ApproxCalculator.listener = listener;
    }

    public static void calculate(double[] mass) throws InterruptedException {
        step = 0;
        Arrays.sort(mass);
        double[] containers = new double[mass.length];
        int[] indexes = new int[mass.length];

        for (int i = mass.length - 1; i >= 0; i--) {
            double a = mass[i];
            for (int j = 0; j < containers.length; j++) {
                step++;
                listener.stateChangedApprox(mass, containers, i, j);
                Thread.sleep(sleep);
                if (containers[j] + a <= 1) {
                    containers[j] += a;
                    indexes[i] = j;
                    step += 2;
                    break;
                }
            }
            step++;
            listener.stateChangedApprox(mass, containers, i, 0);
            Thread.sleep(sleep);
        }

        StringBuilder[] sb = new StringBuilder[mass.length];

        for (int i = 1; i <= mass.length; i++) {
            sb[i - 1] = new StringBuilder("Контейнер №" + i + ": ");
        }

        for (int i = 0; i < indexes.length; i++) {
            sb[indexes[i]].append(mass[i]).append(" ");
        }

        StringBuilder ss = new StringBuilder();
        for (StringBuilder s : sb)
            ss.append(s).append("\n");

        ss.append("\n Кол-во шагов: ").append(step);

        listener.setResultApprox(ss.toString());
    }
}
