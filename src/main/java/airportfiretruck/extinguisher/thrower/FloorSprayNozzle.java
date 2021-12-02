package airportfiretruck.extinguisher.thrower;

import airportfiretruck.extinguisher.watersupply.Tank;


// Note: Ohne Parallelisierung passiert hier natürlich nichts, auch wenn der Selbstschutz eingeschaltet ist.
// Hier wird nur einmal spray() aufgerufen.
public class FloorSprayNozzle extends Thrower {

    private final Tank tank;

    private boolean isOn = false;

    public FloorSprayNozzle(int limit, Tank tank) {
        super(limit);
        this.tank = tank;
    }

    @Override
    public void spray() {
        tank.getLiquid(limit);
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
