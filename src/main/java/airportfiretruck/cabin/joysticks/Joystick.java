package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

import java.util.List;

public abstract class Joystick implements IJoystick {
    protected List<PushButton> pushButtons;
    protected JoystickButton joystickButton;
    protected int ratio = 0;

    public Joystick(List<PushButton> pushButtons, JoystickButton joystickButton) {
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


    protected void joystickButtonPressed(IThrowerMixer thrower) {
        if (thrower.isActive()) {
            thrower.setMixingRatio(ratio);
            thrower.spray();
        }
    }

    public List<PushButton> getPushButtons() {
        return pushButtons;
    }

    public JoystickButton getJoystickButton() {
        return joystickButton;
    }
}
