public class Engine {
    private double currentThrust; // in kN

    public Engine() {
        this.currentThrust = 0.0; // Initialize with zero thrust
    }

    public double getCurrentThrust() {
        return currentThrust;
    }

    public void setThrust(double thrust) {
        this.currentThrust = thrust;
    }

    public double simulateThrust() {
        // Simulate thrust value (for example, increment by a random value)
        this.currentThrust += Math.random() * 10; // Simple simulation
        return this.currentThrust;
    }
}
