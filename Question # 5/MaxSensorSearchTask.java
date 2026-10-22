public class MaxSensorSearchTask extends AbstractSensorSearch {

    public MaxSensorSearchTask(double[] sensorData, int start, int end) {
        super(sensorData, start, end);
    }

    @Override
    public void run() {
        double maxVal = Double.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (sensorData[i] > maxVal) {
                maxVal = sensorData[i];
            }
        }
        this.result = maxVal;
        System.out.println("[" + Thread.currentThread().getName() + "] Maximum Temperature Found in Segment: " + maxVal + "°C");
    }
}