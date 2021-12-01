package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;

import java.util.List;

public interface IJoystick {
    List<PushButton> pushButtons = null;
    JoystickButton joystickButton = null;
}
