package komplexaufgabe2;

import airportfiretruck.extinguisher.IThrowerMixer;

public class Active extends State {
    public Active(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
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
