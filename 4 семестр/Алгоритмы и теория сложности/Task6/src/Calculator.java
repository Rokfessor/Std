import java.util.Arrays;

public class Calculator {
    private static int dp[][];
    private static int W = 100;
    public static void calculate(Obj[] mass){
        int N = mass.length;
        dp = new int[N + 1][W + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                dp[i][j] = dp[i-1][j];
                if (mass[i - 1].v <= j)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - mass[i].v] +mass[i].st);
            }
        }

        System.err.println(Arrays.deepToString(dp));
    }
}
