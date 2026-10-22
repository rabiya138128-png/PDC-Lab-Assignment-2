public class MinSensorSearchTask extends AbstractSensorSearch {

    public MinSensorSearchTask(double[] sensorData, int start, int end) {
        super(sensorData, start, end);
    }

    @Override
    public void run() {
        double minVal = Double.MAX_VALUE;
        for (int i = start; i < end; i++) {
            if (sensorData[i] < minVal) {
                minVal = sensorData[i];
            }
        }
        this.result = minVal;
        System.out.println("[" + Thread.currentThread().getName() + "] Minimum Temperature Found in Segment: " + minVal + "°C");
    }
}