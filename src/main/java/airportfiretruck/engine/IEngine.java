package airportfiretruck.engine;

public interface IEngine {
    boolean isOn = false;
    int velocity = 0;
    public void on();
    public void off();
    public int rotate(int kmph);
}
