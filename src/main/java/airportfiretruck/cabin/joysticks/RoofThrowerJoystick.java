package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;
import airportfiretruck.position.LeftRightSide;

public class RoofThrowerJoystick extends Joystick {

    protected RoofThrower thrower;

    public RoofThrowerJoystick() {
        super();
    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        if (pushButton.getPosition() == LeftRightSide.LEFT) {
            if (pushButton.isActive()) {        // RoofThrower ist deaktiviert und schwenken auf 0°
                thrower.setActive(false);
                thrower.setUpright(false);
                thrower.setUpperSegmentLength(0);
            } else {                            // RoofThrower ist aktiviert und schwenken auf 90°
                thrower.setActive(true);
                thrower.setUpright(true);
                thrower.setUpperSegmentLength(16);
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

    @Override
    public void assign(IThrowerMixer thrower) {
        this.thrower = (RoofThrower) thrower;
    }

}
