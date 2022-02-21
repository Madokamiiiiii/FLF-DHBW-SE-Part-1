package airportfiretruck.buttons;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;
import airportfiretruck.position.LeftRightSide;

public class PushButton implements IButton {
    private final LeftRightSide position;
    private boolean active = false;
    private IJoystick<? extends IThrowerMixer> joystick;

    public PushButton(LeftRightSide leftRightSide) {
        position = leftRightSide;
    }

    public void setJoystick(IJoystick<? extends IThrowerMixer> joystick) {
        this.joystick = joystick;
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
