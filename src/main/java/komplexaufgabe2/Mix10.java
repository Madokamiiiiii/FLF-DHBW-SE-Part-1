package komplexaufgabe2;

import airportfiretruck.extinguisher.IThrowerMixer;

public class Mix10 extends State {
    private final int ratio = 10;

    public Mix10(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
        super(intelligentJoystick);
    }

    @Override
    public void pushButtonPressed() {
        intelligentJoystick.setState(new Final(intelligentJoystick, ratio));
    }

    @Override
    public void joystickButtonPressed() {
        intelligentJoystick.setState(new Mix0(intelligentJoystick));
    }
}
