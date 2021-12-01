package airportfiretruck.engine;

public interface IEngine {
    void on();

    void off();

    int rotate(int kmph);

    int getVelocity();

    BatteryManagement getBatteryManagement();

    void setVelocity(int velocity);
}
