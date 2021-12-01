package airportfiretruck.thrower;

public class RoofThrower implements IThrowerMixer{
    private RoofThrowerLevel level;
    private UpperSegment usegment;
    private LowerSegment lsegment;
    public RoofThrower() {

    }

    public void setLevel(RoofThrowerLevel level) {
        this.level = level;
    }

    @Override
    public void spray() {

    }
}
