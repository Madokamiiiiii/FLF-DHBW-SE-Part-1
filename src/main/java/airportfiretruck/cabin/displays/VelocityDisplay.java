package airportfiretruck.cabin.displays;

public class VelocityDisplay implements IDisplay{
    private int velocity;
    public VelocityDisplay() {
        velocity = 0;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }
    @Override
    public String display() {
        return velocity + "km/h";
    }
}
