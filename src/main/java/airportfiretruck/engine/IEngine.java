package airportfiretruck.engine;

public interface IEngine {
    void on();

    void off();

    boolean isOn();

    int getVelocity();

    void setVelocity(int velocity);

    void rotate();

}
