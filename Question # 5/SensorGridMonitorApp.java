import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Parallel IoT Sensor Telemetry Analytics Engine ===");

        int datasetSize = 50000;
        double[] telemetryData = new double[datasetSize];
        Random rand = new Random(42);
        for (int i = 0; i < datasetSize; i++) {
            telemetryData[i] = 10.0 + (100.0 - 10.0) * rand.nextDouble();
        }

        telemetryData[12500] = 3.14;   // Critical Minimum
        telemetryData[37500] = 189.75; // Peak Vibration Spike

        int numThreads = 4;
        int chunkSize = datasetSize / numThreads;

        ParallelMinSearch[] minTasks = new ParallelMinSearch[numThreads];
        ParallelMaxSearch[] maxTasks = new ParallelMaxSearch[numThreads];
        Thread[] minThreads = new Thread[numThreads];
        Thread[] maxThreads = new Thread[numThreads];

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? datasetSize : (i + 1) * chunkSize;

            minTasks[i] = new ParallelMinSearch(telemetryData, start, end);
            maxTasks[i] = new ParallelMaxSearch(telemetryData, start, end);

            minThreads[i] = new Thread(minTasks[i], "MinWorker-" + i);
            maxThreads[i] = new Thread(maxTasks[i], "MaxWorker-" + i);

            minThreads[i].start();
            maxThreads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            minThreads[i].join();
            maxThreads[i].join();
        }

        double globalMin = Double.MAX_VALUE;
        double globalMax = -Double.MAX_VALUE;

        for (int i = 0; i < numThreads; i++) {
            if (minTasks[i].getResult() < globalMin) {
                globalMin = minTasks[i].getResult();
            }
            if (maxTasks[i].getResult() > globalMax) {
                globalMax = maxTasks[i].getResult();
            }
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1e6;

        System.out.println("\n--- Final Aggregated Telemetry Results ---");
        System.out.println("Global Network Critical Minimum Value: " + globalMin);
        System.out.println("Global Network Peak Vibration Spike:   " + globalMax);
        System.out.println("Execution Time: " + String.format("%.3f", durationMs) + " ms");
        System.out.println("=== IoT Monitoring Execution Finished Successfully ===");
    }
}