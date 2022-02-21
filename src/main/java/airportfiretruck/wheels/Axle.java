package airportfiretruck.wheels;

import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

public abstract class Axle {
    private final FrontRearSide frontRearSide;
    private final LeftRightSide side;
    private List<Wheel> wheels;

    protected Axle(FrontRearSide frontRearSide, LeftRightSide side) {
        this.frontRearSide = frontRearSide;
        this.side = side;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public FrontRearSide getFrontRearSide() {
        return frontRearSide;
    }

    public LeftRightSide getSide() {
        return side;
    }
}
