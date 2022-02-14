package airportfiretruck.buttons;

import airportfiretruck.cabin.BusDoor;
import airportfiretruck.position.FrontRearSide;

public class BusDoorButton extends Button {

    private BusDoor busDoor;
    private final FrontRearSide position;

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
