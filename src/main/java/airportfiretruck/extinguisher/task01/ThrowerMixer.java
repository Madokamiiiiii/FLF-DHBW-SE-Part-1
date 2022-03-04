package airportfiretruck.extinguisher.task01;

import airportfiretruck.extinguisher.IThrowerMixer;
import airportfiretruck.extinguisher.Thrower;

public abstract class ThrowerMixer extends Thrower implements IThrowerMixer {

    protected final MixerReflectionUtil mixerReflectionUtil;
    protected boolean active;
    protected int mixingRatio;

    protected ThrowerMixer(MixerReflectionUtil mixer, int limit) {
        super(limit);
        this.mixerReflectionUtil = mixer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getMixingRatio() {
        return mixingRatio;
    }

    public void setMixingRatio(int ratio) {
        this.mixingRatio = ratio;
    }

    public MixerReflectionUtil getMixer() {
        return mixerReflectionUtil;
    }
}
