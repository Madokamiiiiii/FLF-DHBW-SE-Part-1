package airportfiretruck.wheels;

import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;

public class RearAxle extends Axle {

    public RearAxle(LeftRightSide side) {
        super(FrontRearSide.REAR, side);
    }
}
