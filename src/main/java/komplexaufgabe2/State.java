package komplexaufgabe2;

import airportfiretruck.extinguisher.IThrowerMixer;

public abstract class State {
    protected final IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick;

    protected State(IntelligentJoystick<? extends IThrowerMixer> intelligentJoystick) {
        this.intelligentJoystick = intelligentJoystick;
    }

    public abstract void pushButtonPressed();

    public abstract void joystickButtonPressed();
}
