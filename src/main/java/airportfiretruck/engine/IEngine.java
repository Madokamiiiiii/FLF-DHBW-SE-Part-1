package airportfiretruck.engine;

import airportfiretruck.engine.battery.BatteryManagement;

public interface IEngine {
    void on();

    void off();

    boolean isOn();

    int getVelocity();

    BatteryManagement getBatteryManagement();

    void setVelocity(int velocity);
}
