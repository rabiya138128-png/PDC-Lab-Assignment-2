public class IoTSensorNetworkApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Parallel IoT Sensor Telemetry Analytics Engine ===");

        double[] telemetryReadings = {
            24.5, 31.2, 18.9, 42.1, 15.3, 29.8, 38.4, 11.2,
            27.6, 33.1, 45.0, 19.4, 22.8, 14.1, 36.7, 40.2
        };

        int mid = telemetryReadings.length / 2;

        MinSensorSearchTask minTask = new MinSensorSearchTask(telemetryReadings, 0, telemetryReadings.length);
        MaxSensorSearchTask maxTask = new MaxSensorSearchTask(telemetryReadings, 0, telemetryReadings.length);

        Thread minThread = new Thread(minTask, "Min-Sensor-Worker");
        Thread maxThread = new Thread(maxTask, "Max-Sensor-Worker");

        minThread.start();
        maxThread.start();

        minThread.join();
        maxThread.join();

        System.out.println("\n--- Final Aggregated Telemetry Results ---");
        System.out.println("Global Network Minimum Thermal Reading: " + minTask.getResult() + "°C");
        System.out.println("Global Network Maximum Thermal Reading: " + maxTask.getResult() + "°C");
        System.out.println("=== IoT Monitoring Execution Finished Successfully ===");
    }
}
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Parallel IoT Sensor Telemetry Analytics Engine ===");

        double[] telemetryReadings = {
            24.5, 31.2, 18.9, 42.1, 15.3, 29.8, 38.4, 11.2,
            27.6, 33.1, 45.0, 19.4, 22.8, 14.1, 36.7, 40.2
        };

        int mid = telemetryReadings.length / 2;

        MinSensorSearchTask minTask = new MinSensorSearchTask(telemetryReadings, 0, telemetryReadings.length);
        MaxSensorSearchTask maxTask = new MaxSensorSearchTask(telemetryReadings, 0, telemetryReadings.length);

        Thread minThread = new Thread(minTask, "Min-Sensor-Worker");
        Thread maxThread = new Thread(maxTask, "Max-Sensor-Worker");

        minThread.start();
        maxThread.start();

        minThread.join();
        maxThread.join();

        System.out.println("\n--- Final Aggregated Telemetry Results ---");
        System.out.println("Global Network Minimum Thermal Reading: " + minTask.getResult() + "°C");
        System.out.println("Global Network Maximum Thermal Reading: " + maxTask.getResult() + "°C");
        System.out.println("=== IoT Monitoring Execution Finished Successfully ===");
    }
}