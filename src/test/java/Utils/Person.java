package Utils;

import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.joysticks.Joystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.seats.FrontSeat;
import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

public abstract class Person {

    private final IRotaryKnob throwerKnob;
    private final Joystick joystick;
    private final List<PushButton> joystickPushButtons;
    private final List<BusDoorButton> doorButtons;
    private final FrontSeat seat;

    public Person(Joystick joystick, ControlPanel panel, ThrowerType type, List<BusDoorButton> doorButtons, FrontSeat seat) {
        this.joystick = joystick;
        throwerKnob = panel.getKnobs().stream().filter(knob -> knob.getType() == type).findFirst().orElseThrow();
        joystickPushButtons = joystick.getPushButtons();
        this.doorButtons = doorButtons;
        this.seat = seat;
    }

    public void turnThrower(Direction direction) {
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

    public void pressDoorButton(FrontRearSide position) {
        doorButtons.stream().filter(doorButton -> doorButton.getPosition().equals(position)).findFirst().orElseThrow().pressed();
    }

    public void sitOrGetUp() {
        seat.setOccupied(!seat.isOccupied());
    }

}
