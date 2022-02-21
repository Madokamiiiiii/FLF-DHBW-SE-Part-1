package airportfiretruck.buttons;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public class JoystickButton implements IButton {
    private IJoystick<? extends IThrowerMixer> joystick;

    public void setJoystick(IJoystick<? extends IThrowerMixer> joystick) {
        this.joystick = joystick;
    }

    public void pressed() {
        joystick.joystickButtonPressed();
    }

}
