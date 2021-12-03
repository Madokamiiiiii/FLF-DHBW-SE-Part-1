package komplexaufgabe2;

public abstract class State {
    protected final IntelligentJoystick intelligentJoystick;

    public State(IntelligentJoystick intelligentJoystick) {
        this.intelligentJoystick = intelligentJoystick;
    }

    public abstract void pushButtonPressed();

    public abstract void joystickButtonPressed();
}
