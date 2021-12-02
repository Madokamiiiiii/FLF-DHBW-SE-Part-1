package airportfiretruck.lights;

import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;

public abstract class Light {

    private boolean isOn;

    protected Position position;
    protected FrontRearSide frontRearSide;
    protected LeftRightSide leftRightSide;

    public LeftRightSide getLeftRightSide() {
        return leftRightSide;
    }

    public FrontRearSide getFrontRearSide() {
        return frontRearSide;
    }

    public Position getPosition() {
        return position;
    }

    public void setFrontRearSide(FrontRearSide frontRearSide) {
        this.frontRearSide = frontRearSide;
    }

    public void setLeftRightSide(LeftRightSide leftRightSide) {
        this.leftRightSide = leftRightSide;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }
}
