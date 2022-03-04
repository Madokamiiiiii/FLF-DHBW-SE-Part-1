package airportfiretruck.extinguisher.task01;

import airportfiretruck.extinguisher.Thrower;
import airportfiretruck.extinguisher.tank.ITank;

public class FloorSprayNozzle extends Thrower {

    private final ITank tank;

    private boolean isOn = false;

    public FloorSprayNozzle(int limit, ITank tank) {
        super(limit);
        this.tank = tank;
    }

    @Override
    public void spray() {
        if (isOn) {
            int actual = tank.getLiquid(limit);
            if (actual != limit) {
                throw new RuntimeException("Not enough water in tank");
            }
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public ITank getTank() {
        return tank;
    }
}
