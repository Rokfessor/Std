import java.util.Arrays;

public class Main {
    private static final int INF = 9999;

    public static void main(String[] args) {
        int[][] graph = {
                {0, 11, INF, INF, 8, INF, 11, INF, 17, INF},
                {11, 0, 9, INF, INF, 15, INF, INF, INF, 16},
                {INF, 9, 0, INF, INF, INF, INF, 1, INF, INF},
                {INF, INF, INF, 0, INF, 4, 6, INF, INF, INF},
                {8, INF, INF, INF, 0, INF, 8, INF, INF, 2},
                {INF, 15, INF, 4, INF, 0, 8, 17, INF, INF},
                {11, INF, INF, 6, 8, 8, 0, INF, INF, INF},
                {INF, INF, 1, INF, INF, 17, INF, 0, 9, INF},
                {17, INF, INF, INF, INF, INF, INF, 9, 0, 12},
                {INF, 16, INF, INF, 2, INF, INF, INF, 12, 0}
        };

        int[] vesa = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[][] rasst = floyd(graph);
        System.err.println("Матрица расстояний");
        Arrays.stream(rasst).forEach(ints -> System.err.println(Arrays.toString(ints)));
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                    rasst[i][j] *= vesa[j];

        int[] max = new int[10];
        for (int i = 0; i < 10; i++) {
            int temp = 0;
            for (int j = 0; j < 10; j++)
                if (rasst[i][j] > temp)
                    temp = rasst[i][j];

            max[i] = temp;
        }

        System.err.println("\nПеремножение строк матрицы на вектор весов вершин");

        for (int i = 0; i < 10; i++)
            System.err.println("max" + Arrays.toString(rasst[i]) + " = " + max[i]);

        int res = Arrays.stream(max).min().getAsInt();
        int ind = 0;
        for (int i = 0; i < max.length; i++)
            if (max[i] == res)
                ind = i + 1;
        System.err.println("\nОптимальный центр: " + ind + " вершина со значением " + res);
    }

    public static int[][] floyd(int[][] graph) {
        int v = graph.length;
        int[][] g = Arrays.copyOf(graph, v);

        for (int i = 0; i < v; i++)
            g[i][i] = 0;

        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++)
                for (int k = 0; k < v; k++)
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);

        return g;
    }
}