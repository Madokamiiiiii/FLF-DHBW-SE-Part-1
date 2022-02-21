package komplexaufgabe2;

import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public class Mix0 extends State {
    private final int ratio = 0;

    public Mix0(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
        super(intelligentJoystick);
    }

    @Override
    public void pushButtonPressed() {
        intelligentJoystick.setState(new Final(intelligentJoystick, ratio));
    }

    @Override
    public void joystickButtonPressed() {
        intelligentJoystick.setState(new Active(intelligentJoystick));
    }
}
