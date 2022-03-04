package komplexaufgabe2;

import airportfiretruck.extinguisher.IThrowerMixer;

public class Mix5 extends State {
    private final int ratio = 5;

    public Mix5(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
        super(intelligentJoystick);
    }

    @Override
    public void pushButtonPressed() {
        intelligentJoystick.setState(new Final(intelligentJoystick, ratio));
    }

    @Override
    public void joystickButtonPressed() {
        intelligentJoystick.setState(new Mix10(intelligentJoystick));
    }
}
