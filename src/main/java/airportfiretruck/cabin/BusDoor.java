package airportfiretruck.cabin;

import airportfiretruck.buttons.BusDoorButton;

import java.util.List;

public class BusDoor {

    private boolean isOpen;

    private final List<BusDoorButton> buttons;

    public void openClose() {
        isOpen = !isOpen;
    }

    public BusDoor() {

    }
}
