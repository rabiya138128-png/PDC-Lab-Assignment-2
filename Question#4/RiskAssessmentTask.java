import java.util.concurrent.RecursiveTask;

public class RiskAssessmentTask extends RecursiveTask<Double> {
    private static final int THRESHOLD = 5;
    private final double[] portfolioData;
    private final int start;
    private final int end;

    public RiskAssessmentTask(double[] portfolioData, int start, int end) {
        this.portfolioData = portfolioData;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // Sequential calculation for small workloads
            double totalRisk = 0;
            for (int i = start; i < end; i++) {
                totalRisk += Math.pow(portfolioData[i], 2) * 1.05;
            }
            return totalRisk;
        } else {
            // Divide task into two parallel subtasks using Fork/Join
            int mid = start + (end - start) / 2;
            RiskAssessmentTask leftTask = new RiskAssessmentTask(portfolioData, start, mid);
            RiskAssessmentTask rightTask = new RiskAssessmentTask(portfolioData, mid, end);

            // Asynchronously fork the left task
            leftTask.fork();

            // Compute right task in current thread and join left task result
            double rightResult = rightTask.compute();
            double leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}