package airportfiretruck.extinguisher.thrower;

import airportfiretruck.extinguisher.watersupply.Mixer;

public abstract class ThrowerMixer extends Thrower implements IThrowerMixer {

    protected boolean active;
    protected Mixer mixer;
    protected int mixingRatio;

    public ThrowerMixer(Mixer mixer, int limit) {
        super(limit);
        this.mixer = mixer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMixingRatio(int ratio) {
        this.mixingRatio = ratio;
    }

}
