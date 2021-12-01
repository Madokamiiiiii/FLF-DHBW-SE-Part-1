package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.ButtonPosition;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.thrower.IThrowerMixer;
import airportfiretruck.thrower.RoofThrower;

public class RoofThrowerJoystick extends Joystick {

    protected RoofThrower thrower;

    public RoofThrowerJoystick() {
        super();
    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        if (pushButton.getPosition() == ButtonPosition.LEFT) {
            if (pushButton.isActive()) {        // RoofThrower ist deaktiviert und schwenken auf 0°
                thrower.setActive(false);
                thrower.setLowerSegmentDegree(0);
                thrower.setUpperSegmentLength(0);
            } else {                            // RoofThrower ist aktiviert und schwenken auf 90°
                thrower.setActive(true);
                thrower.setLowerSegmentDegree(90);
                thrower.setUpperSegmentLength(11);
            }
        } else {                                // Rechter Druckknopf
            if (thrower.isActive()) {           // Ändere Mischverhältnis
                next();
            }                                   // Ansonsten tue nichts
        }
    }

    @Override
    public void assignThrower(IThrowerMixer thrower) {
        this.thrower = (RoofThrower) thrower;
    }

    @Override
    public void joystickButtonPressed() {
        joystickButtonPressed(thrower);
    }

}
