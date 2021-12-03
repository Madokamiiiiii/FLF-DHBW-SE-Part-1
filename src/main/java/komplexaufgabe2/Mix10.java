package komplexaufgabe2;

public class Mix10 extends State {
    private final int ratio = 10;

    public Mix10(IntelligentJoystick intelligentJoystick) {
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
