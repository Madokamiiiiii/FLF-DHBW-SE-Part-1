package komplexaufgabe2;

public class Mix3 extends State {
    private final int ratio = 3;

    public Mix3(IntelligentJoystick intelligentJoystick) {
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
