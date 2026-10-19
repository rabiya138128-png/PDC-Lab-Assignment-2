public class LogProcessorTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[LogProcessor] Iteration " + i 
                + " | Thread Name: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // Pause execution for 1000 milliseconds
            } catch (InterruptedException e) {
                System.err.println("[LogProcessor] Thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}