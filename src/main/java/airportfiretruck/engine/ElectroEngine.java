package airportfiretruck.engine;

import airportfiretruck.battery.BatteryManagement;

public class ElectroEngine extends Engine {
    private BatteryManagement batteryManagement;

    public ElectroEngine() {

    }

    @Override
    public void on() {

    }

    @Override
    public void off() {

    }

    @Override
    public int rotate(int kmph) {
        batteryManagement.takeOut(25 * kmph);
        return 0;
    }
}
