package komplexaufgabe2;

import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public class Inactive extends State {

    public Inactive(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
        super(intelligentJoystick);
        intelligentJoystick.setActive(false);
    }

    @Override
    public void pushButtonPressed() {
        intelligentJoystick.setState(new Active(intelligentJoystick));
    }

    @Override
    public void joystickButtonPressed() {
        // Noop
    }
}
