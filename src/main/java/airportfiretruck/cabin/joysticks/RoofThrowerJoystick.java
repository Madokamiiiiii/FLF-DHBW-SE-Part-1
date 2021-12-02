package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.ButtonPosition;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;

public class RoofThrowerJoystick extends Joystick {

    protected RoofThrower thrower;

    public RoofThrowerJoystick(RoofThrower thrower) {
        super();
        this.thrower = thrower;
    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        if (pushButton.getPosition() == ButtonPosition.LEFT) {
            if (pushButton.isActive()) {        // RoofThrowerAlt ist deaktiviert und schwenken auf 0°
                thrower.setActive(false);
                thrower.setLowerSegmentDegree(0);
                thrower.setUpperSegmentLength(0);
            } else {                            // RoofThrowerAlt ist aktiviert und schwenken auf 90°
                thrower.setActive(true);
                thrower.setLowerSegmentDegree(90);
                //thrower.setUpperSegmentLength(11);
            }
        } else {                                // Rechter Druckknopf
            if (thrower.isActive()) {           // Ändere Mischverhältnis
                next();
            }                                   // Ansonsten tue nichts
        }
    }

    @Override
    public void joystickButtonPressed() {
        joystickButtonPressed(thrower);
    }

}
