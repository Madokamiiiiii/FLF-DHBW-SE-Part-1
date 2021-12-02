package airportfiretruck.extinguisher.thrower;

import airportfiretruck.extinguisher.watersupply.Mixer;

public class FrontThrower extends ThrowerMixer {
    private int degree;
    private int level;

    public FrontThrower(Mixer mixer, int limit) {
        super(mixer, limit);
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void spray() {
        mixer.getLiquid(level, mixingRatio);
    }
}
