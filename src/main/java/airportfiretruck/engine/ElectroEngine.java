package airportfiretruck.engine;

import airportfiretruck.battery.BatteryManagement;

public class ElectroEngine implements IEngine{
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
        return 0;
    }
}
