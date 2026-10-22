public abstract class AbstractSensorSearch implements Runnable {
    protected final double[] sensorData;
    protected final int start;
    protected final int end;
    protected double result;

    public AbstractSensorSearch(double[] sensorData, int start, int end) {
        this.sensorData = sensorData;
        this.start = start;
        this.end = end;
    }

    public double getResult() {
        return result;
    }

    @Override
    public abstract void run();
}