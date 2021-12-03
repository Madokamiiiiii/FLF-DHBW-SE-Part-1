package komplexaufgabe2;

public class Inactive extends State {

    public Inactive(IntelligentJoystick intelligentJoystick) {
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
