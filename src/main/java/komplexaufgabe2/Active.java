package komplexaufgabe2;

import airportfiretruck.extinguisher.thrower.ThrowerMixer;

public class Active extends State {
    public Active(IntelligentJoystick<ThrowerMixer> intelligentJoystick) {
        super(intelligentJoystick);
        intelligentJoystick.setActive(true);
    }

    @Override
    public void pushButtonPressed() {
        intelligentJoystick.setState(new Inactive(intelligentJoystick));
    }

    @Override
    public void joystickButtonPressed() {
        intelligentJoystick.setState(new Mix3(intelligentJoystick));
    }
}
