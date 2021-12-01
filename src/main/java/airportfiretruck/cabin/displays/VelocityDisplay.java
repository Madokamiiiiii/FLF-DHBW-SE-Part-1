package airportfiretruck.cabin.displays;

public class VelocityDisplay implements IDisplay {

    private int velocity;

    public VelocityDisplay() {
        velocity = 0;
    }

    @Override
    public void setValue(double velocity) {
        this.velocity = (int) velocity;
    }

    @Override
    public String display() {
        return velocity + "km/h";
    }
}
