package airportfiretruck.extinguisher.thrower;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.watersupply.MixerAlt;

public abstract class ThrowerMixerAlt extends Thrower implements IThrowerMixer {

    protected boolean active;
    protected MixerAlt mixer;
    protected IJoystick joystick;

    public ThrowerMixerAlt(int limit) {
        super(limit);

    }

    public boolean isActive() {
        return active;
    }

    public MixerAlt getMixer() {
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
        // Arm muss Joystick gar nicht kennen!
    }
}
