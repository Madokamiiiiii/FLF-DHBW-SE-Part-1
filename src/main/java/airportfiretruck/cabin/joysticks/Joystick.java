package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;
import airportfiretruck.position.LeftRightSide;

import java.util.ArrayList;

public abstract class Joystick implements IJoystick {
    protected ArrayList<PushButton> pushButtons;
    protected JoystickButton joystickButton;
    protected int ratio = 0;

    public Joystick() {
        pushButtons = new ArrayList<>();
        pushButtons.add(0, new PushButton(LeftRightSide.LEFT));
        pushButtons.add(1, new PushButton(LeftRightSide.RIGHT));
        joystickButton = new JoystickButton();
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

    public ArrayList<PushButton> getPushButtons() {
        return pushButtons;
    }

    public JoystickButton getJoystickButton() {
        return joystickButton;
    }
}
