package airportfiretruck.buttons;

import airportfiretruck.cabin.joysticks.IJoystick;

public class JoystickButton extends Button {
    private IJoystick joystick;

    public void setJoystick(IJoystick joystick) {
        this.joystick = joystick;
    }

    public void pressed() {
        joystick.joystickButtonPressed();
    }

}
