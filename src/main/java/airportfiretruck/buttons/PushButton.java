package airportfiretruck.buttons;

import airportfiretruck.cabin.joysticks.Joystick;

public class PushButton extends Button {
    private boolean active;
    private Joystick joystick;

    public void setJoystick(Joystick joystick) {
        this.joystick = joystick;
    }

    public PushButton() {

    }

    @Override
    public void pressed() {
        joystick.pushButtonPressed(this);
        active = !active;
    }

    public boolean isActive() {
        return active;
    }
}
