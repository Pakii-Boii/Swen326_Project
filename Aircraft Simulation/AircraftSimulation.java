public class AircraftSimulation {
    public static void main(String[] args) {
        // Initialize Aircraft
        Aircraft aircraft = new Aircraft("Boeing 737-800", "CFM56-7B", 130, 10); // Thrust in kN

        // Initialize sensors
        AirspeedSensor airspeedSensor = new AirspeedSensor();
        AltitudeSensor altitudeSensor = new AltitudeSensor();
        AttitudeSensor attitudeSensor = new AttitudeSensor();
        Engine engine = new Engine();

        // Initialize autopilot
        Autopilot autopilot = new Autopilot();

        // Start autopilot control loop
        autopilot.startControlLoop(airspeedSensor, altitudeSensor, attitudeSensor, engine, aircraft);
    }
}
