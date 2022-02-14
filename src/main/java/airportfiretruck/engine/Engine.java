package airportfiretruck.engine;

public abstract class Engine implements IEngine {
    protected boolean isOn = false;
    protected int velocity = 0;

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public void on() {
        isOn = true;
    }

    @Override
    public void off() {
        isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }
}
