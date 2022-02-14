package airportfiretruck.engine;

import airportfiretruck.engine.battery.BatteryManagement;

public interface IEngine {
    void on();

    void off();

    boolean isOn();

    int rotate(int kmph);

    int getVelocity();

    BatteryManagement getBatteryManagement();

    void setVelocity(int velocity);
}
