package airportfiretruck.wheels;

import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;

public class FrontAxle extends Axle {
    private int steeringAngle;

    public FrontAxle(LeftRightSide side) {
        super(FrontRearSide.FRONT, side);
        steeringAngle = 0;
    }

    public void setSteeringAngle(int steeringAngle) {
        this.steeringAngle = steeringAngle;
    }

    public int getSteeringAngle() {
        return steeringAngle;
    }
}
