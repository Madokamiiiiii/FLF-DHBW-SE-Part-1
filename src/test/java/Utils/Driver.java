package Utils;

import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

// This class simulates the driver in the tests
public class Driver extends Person {

    private final List<Pedal> pedals;

    public Driver(List<Pedal> pedals, ControlPanel panel, FrontThrowerJoystick frontThrowerJoystick) {
        super(frontThrowerJoystick, panel, ThrowerType.FRONT);
        this.pedals = pedals;
    }

    public void pressPedal(PedalType type) {
       pedals.stream().filter(pedal -> pedal.getPedalType().equals(type)).findFirst().orElseThrow().pressed();
    }
}
