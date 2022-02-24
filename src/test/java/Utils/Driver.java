package Utils;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.cabin.SteeringWheel;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.cabin.seats.FrontSeat;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

// This class simulates the driver in the tests
public class Driver extends Person {

    private final List<Pedal> pedals;
    private final SteeringWheel steeringWheel;

    public Driver(List<Pedal> pedals, SteeringWheel steeringWheel, ControlPanel panel, FrontThrowerJoystick frontThrowerJoystick, List<BusDoorButton> doorButtons, FrontSeat seat) {
        super(frontThrowerJoystick, panel, ThrowerType.FRONT, doorButtons, seat);
        this.pedals = pedals;
        this.steeringWheel = steeringWheel;
    }

    public void pressPedal(PedalType type) {
        pedals.stream().filter(pedal -> pedal.getPedalType().equals(type)).findFirst().orElseThrow().pressed();
    }

    public void turnSteeringWheel(LeftRightSide side, int degree) {
        if (side.equals(LeftRightSide.LEFT)) {
            steeringWheel.turnLeft(degree);
        } else {
            steeringWheel.turnRight(degree);
        }
    }
}
