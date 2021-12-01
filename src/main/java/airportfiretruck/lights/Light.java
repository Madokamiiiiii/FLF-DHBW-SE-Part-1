package airportfiretruck.lights;

import airportfiretruck.lights.position.FrontRearSide;
import airportfiretruck.lights.position.LeftRightSide;
import airportfiretruck.lights.position.Position;

public abstract class Light {

    private boolean isOn;

    protected Position position;
    protected FrontRearSide frontRearSide;
    protected LeftRightSide leftRightSide;

    public LeftRightSide getLeftRightSide() {
        return leftRightSide;
    }

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }
}
