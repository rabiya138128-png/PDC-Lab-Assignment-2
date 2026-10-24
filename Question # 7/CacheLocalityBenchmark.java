public class CacheLocalityBenchmark {

    private static final int N = 2000;
    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        System.out.println("=== Cache Locality Benchmark Started ===");

        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 1.0;
            }
        }
        long rowMajorTime = 0;
        long colMajorTime = 0;
        for (int it = 0; it < ITERATIONS; it++) {
            rowMajorTime += traverseRowMajor(matrix);
            colMajorTime += traverseColumnMajor(matrix);
        }
        double avgRowTime = (double) rowMajorTime / ITERATIONS / 1_000_000.0;
        double avgColTime = (double) colMajorTime / ITERATIONS / 1_000_000.0;
        double speedup = avgColTime / avgRowTime;

        System.out.println("\n--- Performance Results ---");
        System.out.println("Row-Major Average Time: " + avgRowTime + " ms");
        System.out.println("Column-Major Average Time: " + avgColTime + " ms");
        System.out.println("Speedup Ratio: " + String.format("%.2f", speedup) + "x");
        System.out.println("=== Benchmark Complete ===");
    }
    public static long traverseRowMajor(double[][] matrix) {
        long startTime = System.nanoTime();
        double sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += matrix[i][j];
            }
        }
        return System.nanoTime() - startTime;
    }

    public static long traverseColumnMajor(double[][] matrix) {
        long startTime = System.nanoTime();
        double sum = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                sum += matrix[i][j];
            }
        }
        return System.nanoTime() - startTime;
    }
}