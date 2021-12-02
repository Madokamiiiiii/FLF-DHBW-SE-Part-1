package airportfiretruck.engine;

public abstract class Engine implements IEngine {
    protected boolean isOn = false;
    protected int velocity = 0;

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
