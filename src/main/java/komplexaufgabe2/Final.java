package komplexaufgabe2;

import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public class Final extends State {
    private final int ratio;

    public Final(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick, int ratio) {
        super(intelligentJoystick);
        this.ratio = ratio;
    }

    @Override
    public void pushButtonPressed() {
        switch (ratio) {
            case 0 -> intelligentJoystick.setState(new Mix0(intelligentJoystick));
            case 3 -> intelligentJoystick.setState(new Mix3(intelligentJoystick));
            case 5 -> intelligentJoystick.setState(new Mix5(intelligentJoystick));
            case 10 -> intelligentJoystick.setState(new Mix10(intelligentJoystick));
            default -> throw new IllegalStateException("Unexpected value: " + ratio);
        }
    }

    @Override
    public void joystickButtonPressed() {
        intelligentJoystick.spray(ratio);
    }
}
