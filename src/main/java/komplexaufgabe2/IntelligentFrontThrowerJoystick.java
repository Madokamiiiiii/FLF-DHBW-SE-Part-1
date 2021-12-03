package komplexaufgabe2;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public class IntelligentFrontThrowerJoystick extends IntelligentJoystick {

    private FrontThrower frontThrower;

    public IntelligentFrontThrowerJoystick(PushButton pushButton, JoystickButton joystickButton) {
        super(pushButton, joystickButton);
    }

    @Override
    public void setActive(boolean active) {
        if (active) {
            frontThrower.setActive(true);
            frontThrower.setDegree(90);
        } else {
            frontThrower.setActive(false);
            frontThrower.setDegree(0);
        }
    }

    @Override
    public void spray(int ratio) {
        frontThrower.setMixingRatio(ratio);
        frontThrower.spray();
    }

    @Override
    public void assign(IThrowerMixer thrower) {
        frontThrower = (FrontThrower) thrower;
    }
}
