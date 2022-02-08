package airportfiretruck.cabin;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.centralunit.ICabinCentralUnit;
import airportfiretruck.position.FrontRearSide;

import java.util.ArrayList;
import java.util.List;

public class BusDoor {
    private ICabinCentralUnit centralUnit;
    private boolean isOpen;
    private boolean isLocked;
    private final IdReader idReader;

    private final List<BusDoorButton> buttons;

    public BusDoor() {
        isLocked = false;
        buttons = new ArrayList<>();
        buttons.add(0, new BusDoorButton(FrontRearSide.REAR));
        buttons.add(1, new BusDoorButton(FrontRearSide.FRONT));
        buttons.get(0).setBusDoor(this);
        buttons.get(1).setBusDoor(this);
        idReader = new IdReader(this);
    }

    public void connectToCentralUnit(ICabinCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public IdReader getIdReader() {
        return idReader;
    }

    public void lock() {
        isLocked = !isLocked;
    }

    public boolean state() {
        return isOpen;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock(byte[] code) {
        if (this.idReader.checkCode(code)) {
            centralUnit.unLockDoors();
        }
    }

    public void openClose() {
        isOpen = !isOpen;
    }

    public List<BusDoorButton> getButtons() {
        return buttons;
    }
}
