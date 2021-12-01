package airportfiretruck.wheels;

public class FrontAxle extends Axle {
    private int steeringAngle;

    public FrontAxle() {

    }

    public void setSteeringAngle(int steeringAngle) {
        this.steeringAngle = steeringAngle;
    }

    public int getSteeringAngle() {
        return steeringAngle;
    }
}
