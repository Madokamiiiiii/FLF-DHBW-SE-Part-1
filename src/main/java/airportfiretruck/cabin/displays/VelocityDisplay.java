package airportfiretruck.cabin.displays;

public class VelocityDisplay implements IDisplay {

    private int velocity;

    @Override
    public void setValue(double velocity) {
        this.velocity = (int) velocity;
    }

    @Override
    public String display() {
        return velocity + "km/h";
    }
}
