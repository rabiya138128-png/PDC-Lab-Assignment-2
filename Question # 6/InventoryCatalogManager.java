import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryCatalogManager {
    private static final int NUM_THREADS = 10;
    private static final int OPERATIONS_PER_THREAD = 50000;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== E-Commerce Flash Sale Inventory Concurrency Benchmark ===");  
        benchmarkSynchronizedMap();
        benchmarkConcurrentMap(); 
        System.out.println("=== Benchmark Complete ===");
    }

    public static void benchmarkSynchronizedMap() throws InterruptedException {
        Map<String, Integer> rawMap = new HashMap<>();
        rawMap.put("PROD-001", 1000000);
        Map<String, Integer> syncMap = Collections.synchronizedMap(rawMap);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        long startTime = System.nanoTime();

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    syncMap.get("PROD-001");
                    syncMap.put("PROD-001", 500);
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.nanoTime();
        long durationMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        long totalOps = (long) NUM_THREADS * OPERATIONS_PER_THREAD * 2;
        double throughput = (double) totalOps / (durationMs / 1000.0);
        System.out.println("\n[1] Collections.synchronizedMap Benchmark:");
        System.out.println("   - Execution Time: " + durationMs + " ms");
        System.out.println("   - Throughput: " + String.format("%.2f", throughput) + " ops/sec");
    }
    public static void benchmarkConcurrentMap() throws InterruptedException {
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("PROD-001", 1000000);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        long startTime = System.nanoTime();

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    concurrentMap.get("PROD-001");
                    concurrentMap.put("PROD-001", 500);
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.nanoTime();
        long durationMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        long totalOps = (long) NUM_THREADS * OPERATIONS_PER_THREAD * 2;
        double throughput = (double) totalOps / (durationMs / 1000.0);
        System.out.println("\n[2] ConcurrentHashMap Benchmark:");
        System.out.println("   - Execution Time: " + durationMs + " ms");
        System.out.println("   - Throughput: " + String.format("%.2f", throughput) + " ops/sec");
    }
}