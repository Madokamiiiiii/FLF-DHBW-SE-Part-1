package airportfiretruck.thrower;

public class RoofThrower extends ThrowerMixer {
    private RoofThrowerLevel level;
    private UpperSegment upperSegment;
    private LowerSegment lowerSegment;

    public RoofThrower() {
        joystick.assignThrower(this);
    }

    public void setLevel(RoofThrowerLevel level) {
        this.level = level;
    }


    @Override
    public void spray() {

    }

    public void setLowerSegmentDegree(int degree) {
        lowerSegment.setDegree(degree);
    }

    public void setUpperSegmentLength(int length) {
        upperSegment.setLength(length);
    }

}
