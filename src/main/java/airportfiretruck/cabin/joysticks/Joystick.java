package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

import java.util.List;

public abstract class Joystick<T extends IThrowerMixer> implements IJoystick<T> {
    protected List<PushButton> pushButtons;
    protected JoystickButton joystickButton;
    protected T thrower;
    protected int ratio;

    protected Joystick(List<PushButton> pushButtons, JoystickButton joystickButton) {
        ratio = 0;
        this.pushButtons = pushButtons;
        this.joystickButton = joystickButton;
        joystickButton.setJoystick(this);
        pushButtons.get(0).setJoystick(this);
        pushButtons.get(1).setJoystick(this);
    }

    protected void next() {
        switch (ratio) {
            case 0 -> ratio = 3;
            case 3 -> ratio = 5;
            case 5 -> ratio = 10;
            case 10 -> ratio = 0;
        }
    }

    public void joystickButtonPressed() {
        if (thrower.isActive()) {
            thrower.setMixingRatio(ratio);
            thrower.spray();
        }
    }

    public void assign(T thrower) {
        this.thrower = thrower;
    }

    public List<PushButton> getPushButtons() {
        return pushButtons;
    }

    public JoystickButton getJoystickButton() {
        return joystickButton;
    }
}
