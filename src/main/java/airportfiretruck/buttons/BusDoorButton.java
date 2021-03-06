package airportfiretruck.buttons;

import airportfiretruck.cabin.BusDoor;
import airportfiretruck.position.FrontRearSide;

public class BusDoorButton implements IButton {

    private final FrontRearSide position;
    private BusDoor busDoor;

    public BusDoorButton(FrontRearSide frontRearSide) {
        position = frontRearSide;
    }

    public void setBusDoor(BusDoor busDoor) {
        this.busDoor = busDoor;
    }

    public FrontRearSide getPosition() {
        return position;
    }

    @Override
    public void pressed() {
        busDoor.openClose();
    }
}
