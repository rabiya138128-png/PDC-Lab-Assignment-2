public class CacheLocalityBenchmark {
    private static final int N = 2000;
    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 1.0;
            }
        }
    }
}