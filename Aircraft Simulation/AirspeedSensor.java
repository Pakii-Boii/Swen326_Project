import java.util.Random;

public class AirspeedSensor {
    private Random random = new Random();

    public double readAirspeed() {
        // Simulate airspeed in knots
        return 50 + random.nextDouble() * 450; // Random value between 50 and 500 knots
    }
}
