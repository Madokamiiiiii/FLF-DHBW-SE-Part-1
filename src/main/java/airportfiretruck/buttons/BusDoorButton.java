package airportfiretruck.buttons;

import airportfiretruck.cabin.BusDoor;

public class BusDoorButton extends Button {

    private BusDoor busDoor;

    public void setBusDoor(BusDoor busDoor) {
        this.busDoor = busDoor;
    }

    @Override
    public void pressed() {
        busDoor.openClose();
    }
}
