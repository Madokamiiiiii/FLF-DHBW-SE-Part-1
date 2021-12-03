package airportfiretruck.engine;

import airportfiretruck.engine.battery.BatteryManagement;

public interface IEngine {
    void on();

    void off();

    int rotate(int kmph);

    int getVelocity();

    BatteryManagement getBatteryManagement();

    void setVelocity(int velocity);
}
