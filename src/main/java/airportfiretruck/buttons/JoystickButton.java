package airportfiretruck.buttons;

import airportfiretruck.cabin.joysticks.IJoystick;

public class JoystickButton implements IButton {
    private IJoystick joystick;

    public void setJoystick(IJoystick joystick) {
        this.joystick = joystick;
    }

    public void pressed() {
        joystick.joystickButtonPressed();
    }

}
