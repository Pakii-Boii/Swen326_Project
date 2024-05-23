import java.util.Random;

public class AltitudeSensor {
    private Random random = new Random();

    public double readAltitude() {
        // Simulate altitude in feet
        return -1000 + random.nextDouble() * 51000; // Random value between -1000 and 50000 feet AMSL
    }
}
