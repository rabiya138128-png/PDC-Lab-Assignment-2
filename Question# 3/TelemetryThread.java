public class TelemetryThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[TelemetryHealth] Iteration " + i 
                + " | System CPU & Memory Health Update | Thread Name: " + Thread.currentThread().getName());
            try {
                Thread.sleep(800); // Pause execution for 800 milliseconds
            } catch (InterruptedException e) {
                System.err.println("[TelemetryThread] Thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}