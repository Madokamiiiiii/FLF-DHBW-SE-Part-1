package airportfiretruck.extinguisher.thrower.roof;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.thrower.ThrowerMixer;
import airportfiretruck.extinguisher.watersupply.Mixer;

public class RoofThrower extends ThrowerMixer {
    private RoofThrowerLevel level;
    private final UpperSegment upperSegment;
    private final LowerSegment lowerSegment;

    public RoofThrower(IJoystick joystick, Mixer mixer, int limit, UpperSegment upperSegment, LowerSegment lowerSegment) {
        super(mixer, limit);
        this.upperSegment = upperSegment;
        this.lowerSegment = lowerSegment;
        joystick.assign(this);
    }

    public void setLevel(RoofThrowerLevel level) {
        this.level = level;
    }

    public RoofThrowerLevel getLevel() {
        return level;
    }

    @Override
    public void spray() {
        int outputQuantity = switch (level) {
            case A -> 500;
            case B -> 1000;
            case C -> 2500;
        };

        // Wenn die Kapazität überschritten werden würde, nimm die maximale Kapazität.
        // Nach der Spezifikation dürfte das aber nicht erreicht werden.
        int output = mixer.getLiquid(Math.min(outputQuantity, limit), mixingRatio);
        if (output != Math.min(outputQuantity, limit)) {
            throw new RuntimeException("Not enough water/foam in tank");
        }
    }

    public void setUpright(boolean upright) {
        if (upright) {
            lowerSegment.setDegree(90);
        } else {
            lowerSegment.setDegree(0);
        }
    }

    public void setUpperSegmentLength(int length) {
        upperSegment.setLength(length);
    }

    public LowerSegment getLowerSegment() {
        return lowerSegment;
    }

    public UpperSegment getUpperSegment() {
        return upperSegment;
    }

}
