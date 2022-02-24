package komplexaufgabe2;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public abstract class IntelligentJoystick<T extends IThrowerMixer> implements IJoystick<T> {

    private final PushButton pushButton;
    private final JoystickButton joystickButton;
    protected T thrower;
    private State state;

    protected IntelligentJoystick(PushButton pushButton, JoystickButton joystickButton) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract void setActive(boolean active);

    public void spray(int ratio) {
        thrower.setMixingRatio(ratio);
        thrower.spray();
    }

    public void assign(T thrower) {
        this.thrower = thrower;
        state = new Inactive(this); // Erst hier initialisieren
    }
}
