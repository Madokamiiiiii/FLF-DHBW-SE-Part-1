package airportfiretruck.cabin;

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
import airportfiretruck.cabin.seats.Seat;
import airportfiretruck.centralunit.CentralUnit;

import java.util.ArrayList;
import java.util.List;

public class Cabin {

    private List<Pedal> pedals;
    private List<BusDoor> doors;
    private List<Seat> seats;
    private List<IDisplay> displays;
    private List<IJoystick> joysticks;
    private SteeringWheel steeringWheel;
    private ControlPanel controlPanel;

    public Cabin() {
        controlPanel = new ControlPanel();
        steeringWheel = new SteeringWheel();
        pedals = new ArrayList<>();
        pedals.add(0,new Pedal(PedalType.GAS));
        pedals.add(1,new Pedal(PedalType.BRAKE));
        doors = new ArrayList<>();
        doors.add(0,new BusDoor());
        doors.add(1,new BusDoor());
        seats = new ArrayList<>();
        seats.add(0,new FrontSeat());
        seats.add(1,new FrontSeat());
        seats.add(2,new Seat());
        seats.add(3,new Seat());
        displays = new ArrayList<>();
        displays.add(0, new VelocityDisplay());
        displays.add(1, new BatteryDisplay());
        joysticks = new ArrayList<>();
        joysticks.add(0, new FrontThrowerJoystick());
        joysticks.add(1, new RoofThrowerJoystick());
    }

    public List<IDisplay> getDisplays() {
        return displays;
    }

    public SteeringWheel getSwheel() {
        return steeringWheel;
    }

    public void connectToCentralUnit(CentralUnit centralUnit) {
        controlPanel.connectToCentralUnit(centralUnit);
        for (Pedal pedal : pedals) {
            pedal.connectToCentralUnit(centralUnit);
        }
    }
}
