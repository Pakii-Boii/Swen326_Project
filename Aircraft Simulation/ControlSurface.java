public class ControlSurface {
    private String name;
    private double position; // Position in degrees

    public ControlSurface(String name) {
        this.name = name;
        this.position = 0.0; // Initialize at neutral position
    }

    public String getName() {
        return name;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
