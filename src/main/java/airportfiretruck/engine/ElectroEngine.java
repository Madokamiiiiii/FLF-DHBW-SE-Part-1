package airportfiretruck.engine;

import airportfiretruck.cabin.displays.BatteryDisplay;
import airportfiretruck.cabin.displays.VelocityDisplay;
import airportfiretruck.engine.battery.BatteryManagement;

public class ElectroEngine extends Engine {
    private final BatteryManagement batteryManagement;
    private final VelocityDisplay velocityDisplay;
    private final BatteryDisplay batteryDisplay;

    public ElectroEngine(VelocityDisplay velocityDisplay, BatteryDisplay batteryDisplay) {
        this.velocityDisplay = velocityDisplay;
        this.batteryDisplay = batteryDisplay;
        batteryManagement = BatteryManagement.INSTANCE;
    }

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }

    @Override
    public void rotate() {
        // Hinweis: Dies würde zu leichten Verfälschungen führen, wenn die Geschwindigkeit eine ungerade Zahl ist,
        // da aber nur "gerade" Geschwindigkeiten gefahren werden können, stellt das hier kein Problem dar.
        int requiredEnergy = (int) (12.5 * velocity);
        int actualEnergy = batteryManagement.takeOut(requiredEnergy);
        if (requiredEnergy != actualEnergy) {
            throw new RuntimeException("Not enough energy");
        }
        velocityDisplay.setValue(velocity);
        batteryDisplay.setValue(batteryManagement.getRemainingBatteryLevel());
        // Fahrzeug bewegt sich
    }
}
