package Utils;

import Utils.Direction;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.panel.switches.PanelSwitch;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

// This class simulates the operator in the tests
public class Operator extends Person {
    private final List<PanelSwitch> switches;

    public Operator(ControlPanel panel, RoofThrowerJoystick roofThrowerJoystick) {
        super(roofThrowerJoystick, panel, ThrowerType.ROOF);
        switches = panel.getSwitches();
    }

    public void useSwitch(RelatedDevice device) {
        PanelSwitch panelSwitch = switches.stream().filter(singleSwitch -> singleSwitch.getDevice() == device).findAny().orElseThrow();
        panelSwitch.pressed();
    }
}
