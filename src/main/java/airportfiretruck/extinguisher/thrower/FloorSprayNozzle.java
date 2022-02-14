package airportfiretruck.extinguisher.thrower;

import airportfiretruck.extinguisher.watersupply.Tank;

public class FloorSprayNozzle extends Thrower {

    private final Tank tank;

    private boolean isOn = false;

    public FloorSprayNozzle(int limit, Tank tank) {
        super(limit);
        this.tank = tank;
    }

    @Override
    public void spray() {
        if (isOn) {
            tank.getLiquid(limit);
        }
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Tank getTank() {
        return tank;
    }

    public boolean isOn() {
        return isOn;
    }
}
