import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Autopilot {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startControlLoop(AirspeedSensor airspeedSensor, AltitudeSensor altitudeSensor, 
                                 AttitudeSensor attitudeSensor, Engine engine, Aircraft aircraft) {
        scheduler.scheduleAtFixedRate(() -> {
            sendControlSignal(aircraft, engine);
            verifyAndResendControlSignal(airspeedSensor, altitudeSensor, attitudeSensor, engine, aircraft);
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public void sendControlSignal(Aircraft aircraft, Engine engine) {
        // Send control signals to control surfaces
        for (ControlSurface controlSurface : aircraft.getControlSurfaces()) {
            // Adjust control surfaces to a new position (for example, neutral for simplicity)
            controlSurface.setPosition(0.0); // This is an example; it should be based on autopilot logic
            System.out.println(controlSurface.getName() + " control signal sent. Position set to 0 degrees.");
        }

        // Send control signals to the engine control system
        double newThrust = (engine.getCurrentThrust() + 10) % aircraft.getMaxThrust(); // Simple example
        engine.setThrust(newThrust);
        System.out.println("Engine control signal sent. Thrust set to " + newThrust + " kN.");
    }

    public boolean verifyExecution(double expectedValue, double actualValue, double marginOfError) {
        // Verify execution of control signal
        double lowerBound = expectedValue * (1 - marginOfError / 100);
        double upperBound = expectedValue * (1 + marginOfError / 100);
        System.out.println("Expected: " + expectedValue + ", Actual: " + actualValue + ", LB: " + lowerBound + ", UB: " + upperBound);
        return actualValue >= lowerBound && actualValue <= upperBound;
    }

    public void resendCommand(Aircraft aircraft, Engine engine) {
        // Resend control command
        System.out.println("Resending command...");
        sendControlSignal(aircraft, engine);
    }

    private void verifyAndResendControlSignal(AirspeedSensor airspeedSensor, AltitudeSensor altitudeSensor, 
                                              AttitudeSensor attitudeSensor, Engine engine, Aircraft aircraft) {
        try {
            // Wait 200 milliseconds before verifying
            TimeUnit.MILLISECONDS.sleep(200);

            // Simulate sensor readings
            double airspeed = airspeedSensor.readAirspeed();
            double altitude = altitudeSensor.readAltitude();
            double pitch = attitudeSensor.readPitch();
            double roll = attitudeSensor.readRoll();
            double yaw = attitudeSensor.readYaw();
            double thrust = engine.simulateThrust();

            // Verify execution for control surfaces (assuming the expected position is 0 degrees as set in sendControlSignal)
            boolean controlSurfacesExecutionSuccessful = true;
            for (ControlSurface controlSurface : aircraft.getControlSurfaces()) {
                controlSurfacesExecutionSuccessful &= verifyExecution(0.0, controlSurface.getPosition(), 2); // Expecting 0 degrees as per the example
            }

            // Verify execution for engine thrust
            double expectedThrust = (engine.getCurrentThrust() + 10) % aircraft.getMaxThrust(); // Simple example expectation
            boolean engineExecutionSuccessful = verifyExecution(expectedThrust, thrust, 5);

            boolean executionSuccessful = controlSurfacesExecutionSuccessful && engineExecutionSuccessful;

            if (!executionSuccessful) {
                // Resend command up to three times
                for (int i = 0; i < 3; i++) {
                    System.out.print(i + " ");
                    resendCommand(aircraft, engine);
                    TimeUnit.MILLISECONDS.sleep(200);
                    airspeed = airspeedSensor.readAirspeed();
                    altitude = altitudeSensor.readAltitude();
                    pitch = attitudeSensor.readPitch();
                    roll = attitudeSensor.readRoll();
                    yaw = attitudeSensor.readYaw();
                    thrust = engine.simulateThrust();

                    // Verify execution again
                    controlSurfacesExecutionSuccessful = true;
                    for (ControlSurface controlSurface : aircraft.getControlSurfaces()) {
                        controlSurfacesExecutionSuccessful &= verifyExecution(0.0, controlSurface.getPosition(), 2);
                    }
                    engineExecutionSuccessful = verifyExecution(expectedThrust, thrust, 5);

                    executionSuccessful = controlSurfacesExecutionSuccessful && engineExecutionSuccessful;

                    if (executionSuccessful) {
                        System.out.println("\nCommand successfully executed after resend.\n");
                        break;
                    }
                }
                if (!executionSuccessful) {
                    System.out.println("Failed to execute command after three attempts. Alerting pilot...");
                }
            } else {
                System.out.println("\n\nCommand successfully executed.\n\n");
            }

            // Perform fault detection and mitigation
            aircraft.checkSensorValues(airspeed, altitude, pitch, roll, yaw);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
