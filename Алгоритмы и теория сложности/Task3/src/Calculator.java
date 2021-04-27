import java.util.Arrays;

public class Calculator {
    private static CalcListener listener;
    private static long sleep = 1;
    private static int N, Box, OptBox;
    private static double[] V, A;
    private static int[] Cont, OptCont;
    private static double eps = 0.00001F;
    private static long step = 0;


    public static void setListener(CalcListener listener) {
        Calculator.listener = listener;
    }

    private static void pack(int i) throws InterruptedException {
        for (int j = 0; j < N; j++) {
            step++;
            listener.stateChangedExact(V, Cont, OptCont);
            Thread.sleep(sleep);
            if ((V[j] <= eps) && (Box + 1 >= OptBox)) {
                step++;
                break;
            }
            if ((j > 1) && (V[j - 1] <= eps)) {
                step++;
                break;
            }

            if (V[j] + A[i] <= (1 + eps)) {
                step++;
                listener.stateChangedExact(V, Cont, OptCont);
                Thread.sleep(sleep);
                int B = Box;
                step++;
                if (V[j] <= eps) {
                    Box++;
                    step += 2;
                }


                Cont[i] = j + 1;
                V[j] = V[j] + A[i];
                step += 2;
                listener.stateChangedExact(V, Cont, OptCont);
                Thread.sleep(sleep);
                if (i < N - 1) {
                    step += 2;
                    pack(i + 1);
                } else {
                    for (int k = 0; k < N; k++) {
                        listener.stateChangedExact(V, Cont, OptCont);
                        Thread.sleep(sleep);
                        OptCont[k] = Cont[k];
                        step += 2;
                    }
                    OptBox = Box;
                    step++;
                }
                V[j] = V[j] - A[i];
                Cont[i] = 0;
                Box = B;
                step += 3;
                listener.stateChangedExact(V, Cont, OptCont);
                Thread.sleep(sleep);
            }
        }
    }

    public static void calculate(double[] m) throws InterruptedException {
        A = m;
        System.err.println(Arrays.toString(m));
        N = A.length;
        V = new double[N];
        OptCont = new int[N];
        Cont = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = 0;
            Cont[i] = 0;
        }
        Cont[0] = 1;
        V[0] = A[0];
        Box = 1;
        for (int i = 0; i < N; i++) {
            OptCont[i] = i + 1;
        }
        OptBox = N;
        step = 0;
        pack(1);

        StringBuilder[] sb = new StringBuilder[m.length];

        for (int i = 1; i <= sb.length; i++) {
            sb[i - 1] = new StringBuilder("Контейнер №" + i + ": ");
        }

        for (int i = 0; i < N; i++) {
            sb[OptCont[i] - 1].append(A[i]).append(" ");
        }

        StringBuilder ss = new StringBuilder();
        for (StringBuilder s : sb)
            ss.append(s).append("\n");

        ss.append("\n Кол-во шагов: ").append(step);

        listener.setResultExact(ss.toString());
    }
}


