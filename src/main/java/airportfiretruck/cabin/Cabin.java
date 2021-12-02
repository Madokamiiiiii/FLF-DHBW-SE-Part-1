package airportfiretruck.cabin;

import airportfiretruck.cabin.displays.IDisplay;
import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.seats.Seat;

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

    }

    public List<IDisplay> getDisplays() {
        return displays;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

}
