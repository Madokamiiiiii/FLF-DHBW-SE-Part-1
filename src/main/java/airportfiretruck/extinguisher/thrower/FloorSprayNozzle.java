package airportfiretruck.extinguisher.thrower;

import airportfiretruck.extinguisher.watersupply.Tank;

public class FloorSprayNozzle extends Thrower {

    private final Tank tank;

    public FloorSprayNozzle(int limit, Tank tank) {
        super(limit);
        this.tank = tank;
    }

    @Override
    public void spray() {
        tank.getLiquid(limit);
    }
}
