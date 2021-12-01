package airportfiretruck.cabin;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.buttons.ButtonPosition;

import java.util.ArrayList;
import java.util.List;

public class BusDoor {

    private boolean isOpen;

    private final List<BusDoorButton> buttons;

    public void openClose() {
        isOpen = !isOpen;
    }

    public BusDoor() {
        buttons = new ArrayList<>();
        buttons.add(0, new BusDoorButton());
        buttons.add(1, new BusDoorButton());
        buttons.get(0).setBusDoor(this);
        buttons.get(0).setPosition(ButtonPosition.INSIDE);
        buttons.get(1).setBusDoor(this);
        buttons.get(1).setPosition(ButtonPosition.OUTSIDE);
    }
}
