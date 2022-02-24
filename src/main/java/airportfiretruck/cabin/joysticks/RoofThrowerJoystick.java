package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

public class RoofThrowerJoystick extends Joystick<RoofThrower> {

    public RoofThrowerJoystick(List<PushButton> pushButtonList, JoystickButton joystickButton) {
        super(pushButtonList, joystickButton);
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
}
