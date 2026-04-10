public class StandardFarePolicy implements FarePolicy {
    private static final double BASE = 50.0;
    private static final double PER_KM = 6.6666666667;

    public double fareFor(double km) {
        double fare = BASE + km * PER_KM;
        return Math.round(fare * 100.0) / 100.0;
    }
}
