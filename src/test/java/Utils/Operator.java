package Utils;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.panel.switches.PanelSwitch;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.seats.FrontSeat;

import java.util.List;

// This class simulates the operator in the tests
public class Operator extends Person {
    private final List<PanelSwitch> switches;

    public Operator(ControlPanel panel, RoofThrowerJoystick roofThrowerJoystick, List<BusDoorButton> doorButtons, FrontSeat seat) {
        super(roofThrowerJoystick, panel, ThrowerType.ROOF, doorButtons, seat);
        switches = panel.getSwitches();
    }

    public void useSwitch(RelatedDevice device) {
        PanelSwitch panelSwitch = switches.stream().filter(singleSwitch -> singleSwitch.getDevice() == device).findAny().orElseThrow();
        panelSwitch.pressed();
    }
}
