package komplexaufgabe2;

import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public class Mix3 extends State {
    private final int ratio = 3;

    public Mix3(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
        super(intelligentJoystick);
    }

    @Override
    public void pushButtonPressed() {
        intelligentJoystick.setState(new Final(intelligentJoystick, ratio));
    }

    @Override
    public void joystickButtonPressed() {
        intelligentJoystick.setState(new Mix5(intelligentJoystick));
    }
}
