package airportfiretruck.lights;

import airportfiretruck.lights.position.FrontRearSide;
import airportfiretruck.lights.position.LeftRightSide;
import airportfiretruck.lights.position.Position;

public abstract class Light {
    private boolean isOn;

    protected Position pos;
    protected FrontRearSide frside;
    protected LeftRightSide lrside;

    public void on() {

    }
    public void off() {

    }
}
