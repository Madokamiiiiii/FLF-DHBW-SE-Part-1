package airportfiretruck.engine;

import airportfiretruck.engine.battery.BatteryManagement;

public class ElectroEngine extends Engine {
    private final BatteryManagement batteryManagement = BatteryManagement.INSTANCE;

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }
}
