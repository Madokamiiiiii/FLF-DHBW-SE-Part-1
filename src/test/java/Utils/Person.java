package Utils;

import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.joysticks.Joystick;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.panel.switches.PanelSwitch;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

public abstract class Person {

    private IRotaryKnob throwerKnob;
    private final Joystick joystick;
    private List<PushButton> joystickPushButtons;

    public Person(Joystick joystick, ControlPanel panel, ThrowerType type) {
        this.joystick = joystick;
        throwerKnob = panel.getKnobs().stream().filter(knob -> knob.getType() == type).findFirst().orElseThrow();
        joystickPushButtons = joystick.getPushButtons();
    }

    public void turnRoofThrower(Direction direction) {
        if (direction == Direction.LEFT) {
            throwerKnob.turnLeft();
        } else {
            throwerKnob.turnRight();
        }
    }

    public void pressPushButton(LeftRightSide side) {
        joystick.pushButtonPressed(joystickPushButtons.stream().filter(pushButton -> pushButton.getPosition().equals(side)).findFirst().orElseThrow());
    }

    public void pressJoystickButton() {
        joystick.joystickButtonPressed();
    }

}
