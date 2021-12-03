package airportfiretruck.wheels;

import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

public class RearAxle extends Axle {
    private List<Wheel> wheels;

    public RearAxle(LeftRightSide side) {
        super(FrontRearSide.REAR, side);
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }
}
