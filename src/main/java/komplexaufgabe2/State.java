package komplexaufgabe2;

import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public abstract class State {
    protected final IntelligentJoystick<IThrowerMixer> intelligentJoystick;

    protected State(IntelligentJoystick<IThrowerMixer> intelligentJoystick) {
        this.intelligentJoystick = intelligentJoystick;
    }

    public abstract void pushButtonPressed();

    public abstract void joystickButtonPressed();
}
