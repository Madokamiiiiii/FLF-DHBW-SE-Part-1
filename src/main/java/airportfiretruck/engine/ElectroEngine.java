package airportfiretruck.engine;

import airportfiretruck.battery.BatteryManagement;

public class ElectroEngine extends Engine {
    private BatteryManagement batteryManagement;

    public ElectroEngine() {

    }

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }

    @Override
    public void on() {
        isOn = true;
    }

    @Override
    public void off() {
        isOn = false;
    }

    @Override
    public int rotate(int kmph) {
        batteryManagement.takeOut(25 * kmph);
        return 0;
    }
}
