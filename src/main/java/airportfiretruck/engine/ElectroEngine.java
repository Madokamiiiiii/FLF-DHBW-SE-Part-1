package airportfiretruck.engine;

import airportfiretruck.battery.BatteryManagement;

public class ElectroEngine extends Engine {
    private BatteryManagement bmanagement;

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
        bmanagement.takeOut(25 * kmph);
        return 0;
    }
}
