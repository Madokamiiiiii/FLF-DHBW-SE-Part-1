package komplexaufgabe2;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;

public class IntelligentRoofThrowerJoystick extends IntelligentJoystick<RoofThrower> {

    public IntelligentRoofThrowerJoystick(PushButton pushButton, JoystickButton joystickButton) {
        super(pushButton, joystickButton);
    }

    @Override
    public void setActive(boolean active) {
        if (active) {                       // RoofThrower ist deaktiviert und schwenken auf 0°
            thrower.setActive(false);
            thrower.setUpright(false);
            thrower.setUpperSegmentLength(0);
        } else {                            // RoofThrower ist aktiviert und schwenken auf 90°
            thrower.setActive(true);
            thrower.setUpright(true);
            thrower.setUpperSegmentLength(16);
        }
    }
}
