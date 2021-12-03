package komplexaufgabe2;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.joysticks.IJoystick;

public abstract class IntelligentJoystick implements IJoystick {

    private final PushButton pushButton;
    private final JoystickButton joystickButton;
    private State state;
    protected int ratio = 0;

    public IntelligentJoystick(PushButton pushButton, JoystickButton joystickButton) {
        this.pushButton = pushButton;
        this.joystickButton = joystickButton;
    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        state.pushButtonPressed();
    }

    @Override
    public void joystickButtonPressed() {
        state.joystickButtonPressed();
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract void setActive(boolean active);

    public abstract void spray(int ratio);
}
