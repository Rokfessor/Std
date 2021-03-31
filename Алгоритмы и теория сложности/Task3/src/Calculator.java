import java.util.Arrays;

public class Calculator {
    private static CalcListener listener;
    private static long sleep = 1000;
    private static int N, Box, OptBox;
    private static double[] V, Cont, A, OptCont;
    private static double eps = 0.00001F;

    public static void setListener(CalcListener listener) {
        Calculator.listener = listener;
    }

    private static void pack(int i) throws InterruptedException {
        for (int j = 0; j < N; j++) {
            listener.stateChanged(V, Cont, OptCont);
            Thread.sleep(sleep);
            if ((V[i] <= eps) && (Box + 1 >= OptBox))
                break;
            if ((j > 0) && (V[j - 1] <= eps))
                break;
            if (V[j] + A[i] <= (1 + eps)) {
                listener.stateChanged(V, Cont, OptCont);
                Thread.sleep(sleep);
                int B = Box;
                if (V[j] <= eps)
                    Box = Box + 1;
                Cont[i] = j;
                V[j] = V[j] + A[i];
                if (i < N - 1)
                    pack(i + 1);
                else {
                    for (int k = 0; k < N; k++)
                        OptCont[k] = Cont[k];
                    OptBox = Box;
                }
                V[j] = V[j] - A[i];
                Cont[i] = 0;
                Box = B;
                listener.stateChanged(V, Cont, OptCont);
                Thread.sleep(sleep);
            }
        }
    }

    public static void calculate(double[] m) throws InterruptedException {
        A = m;
        System.err.println(Arrays.toString(m));
        N = A.length;
        V = new double[N];
        OptCont = new double[N];
        Cont = new double[N];
        for (int i = 0; i < N; i++) {
            V[i] = 0;
            Cont[i] = 0;
        }
        Cont[0] = 1;
        V[0] = A[0];
        Box = 1;
        for (int i = 0; i < N; i++) {
            OptCont[i] = i;
        }
        OptBox = N;
        pack(1);
        System.err.println(Arrays.toString(OptCont));
    }
}


