package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.ButtonPosition;
import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.thrower.IThrowerMixer;

import java.util.ArrayList;

public abstract class Joystick implements IJoystick {
    protected ArrayList<PushButton> pushButtons;
    protected JoystickButton joystickButton;
    protected int ratio = 0;

    public Joystick() {
        pushButtons = new ArrayList<>();
        pushButtons.add(0, new PushButton());
        pushButtons.add(1, new PushButton());
        joystickButton = new JoystickButton();
        joystickButton.setJoystick(this);
        joystickButton.setPosition(ButtonPosition.BEHIND);
        pushButtons.get(0).setJoystick(this);
        pushButtons.get(0).setPosition(ButtonPosition.LEFT);
        pushButtons.get(1).setJoystick(this);
        pushButtons.get(1).setPosition(ButtonPosition.RIGHT);
    }

    protected void next() {
        switch (ratio) {
            case 0 -> ratio = 3;
            case 3 -> ratio = 5;
            case 5 -> ratio = 10;
            case 10 -> ratio = 0;
            default -> {
            }
        }
    }
    

    protected void joystickButtonPressed(IThrowerMixer thrower) {
        if (thrower.isActive()) {
            thrower.setMixingRatio(ratio);
            thrower.spray();
        }
    }
}
