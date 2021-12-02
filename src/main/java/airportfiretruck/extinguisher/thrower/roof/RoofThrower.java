package airportfiretruck.extinguisher.thrower.roof;

import airportfiretruck.extinguisher.thrower.ThrowerMixer;
import airportfiretruck.extinguisher.watersupply.Mixer;

public class RoofThrower extends ThrowerMixer {
    private RoofThrowerLevel level;
    private final UpperSegment upperSegment;
    private final LowerSegment lowerSegment;

    public RoofThrower(Mixer mixer, int limit, UpperSegment upperSegment, LowerSegment lowerSegment) {
        super(mixer, limit);
        this.upperSegment = upperSegment;
        this.lowerSegment = lowerSegment;
    }

    public void setLevel(RoofThrowerLevel level) {
        this.level = level;
    }


    @Override
    public void spray() {
        switch (level) {
            case A -> mixer.getLiquid(500, mixingRatio);
            case B -> mixer.getLiquid(1000, mixingRatio);
            case C -> mixer.getLiquid(2500, mixingRatio);
        }
    }

    public void setLowerSegmentDegree(int degree) {
        lowerSegment.setDegree(degree);
    }

    public void setUpperSegmentLength(int length) {
        upperSegment.setLength(length);
    }

}
