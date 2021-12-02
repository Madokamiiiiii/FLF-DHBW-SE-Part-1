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
        int amount = 0;
        switch (level) {
            case A -> amount = 500;
            case B -> amount = 1000;
            case C -> amount = 2500;
        }
        // evaluate return value? 2 : both tanks over-emptied, ±1 : water / foam tank overemptied, 0 : all water delivered
        mixer.getLiquid(amount);
    }

    public void setLowerSegmentDegree(int degree) {
        lowerSegment.setDegree(degree);
    }

    public void setUpperSegmentLength(int length) {
        upperSegment.setLength(length);
    }

}
