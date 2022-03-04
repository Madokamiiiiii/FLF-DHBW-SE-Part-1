package komplexaufgabe2;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.task01.FrontThrower;

public class IntelligentFrontThrowerJoystick extends IntelligentJoystick<FrontThrower> {

    public IntelligentFrontThrowerJoystick(PushButton pushButton, JoystickButton joystickButton) {
        super(pushButton, joystickButton);
    }

    @Override
    public void setActive(boolean active) {
        if (active) {
            thrower.setActive(true);
            thrower.setDegree(90);
        } else {
            thrower.setActive(false);
            thrower.setDegree(0);
        }
    }
}
