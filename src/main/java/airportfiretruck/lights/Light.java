package airportfiretruck.lights;

import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;

public abstract class Light {

    protected Position position;
    protected FrontRearSide frontRearSide;
    protected LeftRightSide leftRightSide;
    private boolean isOn;

    public LeftRightSide getLeftRightSide() {
        return leftRightSide;
    }

    public void setLeftRightSide(LeftRightSide leftRightSide) {
        this.leftRightSide = leftRightSide;
    }

    public FrontRearSide getFrontRearSide() {
        return frontRearSide;
    }

    public void setFrontRearSide(FrontRearSide frontRearSide) {
        this.frontRearSide = frontRearSide;
    }

    public Position getPosition() {
        return position;
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

    public boolean isOn() {
        return isOn;
    }
}
