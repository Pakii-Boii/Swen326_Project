import java.util.Random;

public class AttitudeSensor {
    public double readPitch() {
        // Simulate pitch data between -30 to 30 degrees
        return -30 + new Random().nextDouble() * 60;
    }

    public double readRoll() {
        // Simulate roll data between -60 to 60 degrees
        return -60 + new Random().nextDouble() * 120;
    }

    public double readYaw() {
        // Simulate yaw data between -180 to 180 degrees
        return -180 + new Random().nextDouble() * 360;
    }
}
