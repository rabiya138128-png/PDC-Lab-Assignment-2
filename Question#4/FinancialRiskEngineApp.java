import java.util.concurrent.ForkJoinPool;

public class FinancialRiskEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Financial Portfolio Risk Analytics Engine (Fork/Join) ===");

        double[] portfolioMetrics = {
            12.5, 45.2, 88.1, 10.4, 32.6, 77.3, 91.0, 14.8,
            22.4, 56.7, 63.2, 81.1, 19.5, 42.0, 33.3, 95.2
        };

        System.out.println("Dataset Size: " + portfolioMetrics.length + " portfolio elements.");

        ForkJoinPool pool = new ForkJoinPool();
        RiskAssessmentTask rootTask = new RiskAssessmentTask(portfolioMetrics, 0, portfolioMetrics.length);

        long startTime = System.nanoTime();
        double totalCalculatedRisk = pool.invoke(rootTask);
        long endTime = System.nanoTime();

        System.out.println("Total Aggregate Risk Index: " + String.format("%.2f", totalCalculatedRisk));
        System.out.println("Execution Time: " + (endTime - startTime) / 1e6 + " ms");
        System.out.println("=== Parallel Risk Computation Finished Successfully ===");
    }
}