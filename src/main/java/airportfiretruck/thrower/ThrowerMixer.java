package airportfiretruck.thrower;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.thrower.watersupply.Mixer;

public abstract class ThrowerMixer extends Thrower implements IThrowerMixer {

    protected boolean active;
    protected Mixer mixer;
    protected IJoystick joystick;

    public ThrowerMixer() {

    }

    public boolean isActive() {
        return active;
    }

    public Mixer getMixer() {
        return mixer;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMixingRatio(int ratio) {
        mixer.setMixingRatio(ratio);
    }

    // TODO: Wie/Wann könnte die Verbindung hergestellt werden?
    public void assignJoystick() {

    }
}
