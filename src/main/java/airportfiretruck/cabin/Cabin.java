package airportfiretruck.cabin;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.displays.BatteryDisplay;
import airportfiretruck.cabin.displays.IDisplay;
import airportfiretruck.cabin.displays.VelocityDisplay;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.cabin.seats.FrontSeat;
import airportfiretruck.cabin.seats.Respirator;
import airportfiretruck.cabin.seats.Seat;
import airportfiretruck.centralunit.CentralUnit;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;
import airportfiretruck.position.LeftRightSide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cabin {

    private List<Pedal> pedals;
    private List<BusDoor> doors;
    private List<Seat> seats;
    private List<IDisplay> displays;
    private IJoystick<FrontThrower> frontThrowerJoystick;
    private IJoystick<RoofThrower> roofThrowerJoystick;
    private SteeringWheel steeringWheel;
    private ControlPanel controlPanel;

    public void build() {
        controlPanel = new ControlPanel();
        controlPanel.build();
        steeringWheel = new SteeringWheel();
        pedals = new ArrayList<>();
        pedals.add(0, new Pedal(PedalType.GAS));
        pedals.add(1, new Pedal(PedalType.BRAKE));
        doors = new ArrayList<>();
        doors.add(0, new BusDoor());
        doors.add(1, new BusDoor());
        seats = new ArrayList<>();
        seats.add(0, new FrontSeat(new Respirator(), LeftRightSide.RIGHT));
        seats.add(1, new FrontSeat(new Respirator(), LeftRightSide.LEFT));
        for (int i = 0; i < 2; i++) {
            seats.add(new Seat(new Respirator()));
        }
        displays = new ArrayList<>();
        displays.add(0, new VelocityDisplay());
        displays.add(1, new BatteryDisplay());
        frontThrowerJoystick = new FrontThrowerJoystick(Arrays.asList(new PushButton(LeftRightSide.LEFT), new PushButton(LeftRightSide.RIGHT)), new JoystickButton());
        roofThrowerJoystick = new RoofThrowerJoystick(Arrays.asList(new PushButton(LeftRightSide.LEFT), new PushButton(LeftRightSide.RIGHT)), new JoystickButton());
    }

    public List<IDisplay> getDisplays() {
        return displays;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public IJoystick<FrontThrower> getFrontThrowerJoystick() {
        return frontThrowerJoystick;
    }

    public IJoystick<RoofThrower> getRoofThrowerJoystick() {
        return roofThrowerJoystick;
    }

    public List<BusDoor> getDoors() {
        return doors;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public List<Pedal> getPedals() {
        return pedals;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void connectToCentralUnit(CentralUnit centralUnit) {
        steeringWheel.connectToCentralUnit(centralUnit);
        controlPanel.connectToCentralUnit(centralUnit);
        for (Pedal pedal : pedals) {
            pedal.connectToCentralUnit(centralUnit);
        }
    }
}
