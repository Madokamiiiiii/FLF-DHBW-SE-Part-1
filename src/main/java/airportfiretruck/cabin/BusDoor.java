package airportfiretruck.cabin;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.position.FrontRearSide;

import java.util.ArrayList;
import java.util.List;

public class BusDoor {

    private boolean isOpen = false;

    private final List<BusDoorButton> buttons;

    public void openClose() {
        isOpen = !isOpen;
    }

    public BusDoor() {
        buttons = new ArrayList<>();
        buttons.add(0, new BusDoorButton(FrontRearSide.REAR));
        buttons.add(1, new BusDoorButton(FrontRearSide.FRONT));
        buttons.get(0).setBusDoor(this);
        buttons.get(1).setBusDoor(this);
    }
}
