package airportfiretruck.engine;

public interface IEngine {
    public void on();
    public void off();
    public int rotate(int kmph);
    public int getVelocity();
    public void setVelocity(int velocity);
}
