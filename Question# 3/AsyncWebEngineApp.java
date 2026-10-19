public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Asynchronous Cloud Infrastructure Diagnostics Engine ===");

        System.out.println("Default JVM Master Thread Name: " + Thread.currentThread().getName());

        TelemetryThread telemetryWorker = new TelemetryThread();
        telemetryWorker.setName("Telemetry-Worker");

        LogProcessorTask logTask = new LogProcessorTask();
        Thread logWorker = new Thread(logTask);
        logWorker.setName("Log-Worker");

        System.out.println("[State Check] " + telemetryWorker.getName() + " State: " + telemetryWorker.getState());
        System.out.println("[State Check] " + logWorker.getName() + " State: " + logWorker.getState());

        System.out.println("--- Starting Concurrent Threads ---");

        telemetryWorker.start();
        logWorker.start();

        try {
            telemetryWorker.join();
            logWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=== Master Application Execution Completed ===");
    }
}