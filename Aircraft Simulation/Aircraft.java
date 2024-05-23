import java.util.ArrayList;
import java.util.List;

public class Aircraft {
    private String name;
    private String engineType;
    private double maxThrust; // in kN
    private double minThrust; // in kN
    private List<ControlSurface> controlSurfaces;

    public Aircraft(String name, String engineType, double maxThrust, double minThrust) {
        this.name = name;
        this.engineType = engineType;
        this.maxThrust = maxThrust;
        this.minThrust = minThrust;
        this.controlSurfaces = new ArrayList<>();
        this.controlSurfaces.add(new ControlSurface("Elevator"));
        this.controlSurfaces.add(new ControlSurface("Aileron"));
        this.controlSurfaces.add(new ControlSurface("Rudder"));
    }

    // Getters and setters...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getMaxThrust() {
        return maxThrust;
    }

    public void setMaxThrust(double maxThrust) {
        this.maxThrust = maxThrust;
    }

    public double getMinThrust() {
        return minThrust;
    }

    public void setMinThrust(double minThrust) {
        this.minThrust = minThrust;
    }

    public List<ControlSurface> getControlSurfaces() {
        return controlSurfaces;
    }

    // Fault detection and mitigation methods
    public void checkSensorValues(double airspeed, double altitude, double pitch, double roll, double yaw) {
        // Check for abnormal sensor values
        if (airspeed < 50 || airspeed > 500) {
            // Handle abnormal airspeed
            System.out.println("Abnormal airspeed detected. Initiating corrective action...");
            // Implement corrective action here
        }

        if (altitude < -1000 || altitude > 50000) {
            // Handle abnormal altitude
            System.out.println("Abnormal altitude detected. Initiating corrective action...");
            // Implement corrective action here
        }

        if (pitch < -30 || pitch > 30 || roll < -60 || roll > 60 || yaw < -180 || yaw > 180) {
            // Handle abnormal attitude
            System.out.println("Abnormal attitude detected. Initiating corrective action...");
            // Implement corrective action here
        }
    }
}
