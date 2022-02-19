package Utils;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.cabin.seats.FrontSeat;

import java.util.List;

// This class simulates the driver in the tests
public class Driver extends Person {

    private final List<Pedal> pedals;

    public Driver(List<Pedal> pedals, ControlPanel panel, FrontThrowerJoystick frontThrowerJoystick, List<BusDoorButton> doorButtons, FrontSeat seat) {
        super(frontThrowerJoystick, panel, ThrowerType.FRONT, doorButtons, seat);
        this.pedals = pedals;
    }

    public void pressPedal(PedalType type) {
       pedals.stream().filter(pedal -> pedal.getPedalType().equals(type)).findFirst().orElseThrow().pressed();
    }
}
