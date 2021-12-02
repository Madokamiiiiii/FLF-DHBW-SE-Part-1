package airportfiretruck.buttons;

import airportfiretruck.cabin.joysticks.Joystick;
import airportfiretruck.position.LeftRightSide;

public class PushButton extends Button {
    private boolean active = false;
    private Joystick joystick;

    private final LeftRightSide position;

    public void setJoystick(Joystick joystick) {
        this.joystick = joystick;
    }

    public PushButton(LeftRightSide leftRightSide) {
        position = leftRightSide;
    }

    @Override
    public void pressed() {
        joystick.pushButtonPressed(this);
        active = !active;
    }

    public boolean isActive() {
        return active;
    }

    public LeftRightSide getPosition() {
        return position;
    }
}
