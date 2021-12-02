package airportfiretruck.cabin.panel.rotaryknobs;

import airportfiretruck.extinguisher.thrower.roof.RoofThrowerLevel;

public class RoofThrowerKnob implements IRotaryKnob {

    private RoofThrowerLevel level;
    private final ThrowerType type;

    @Override
    public ThrowerType getType() {
        return type;
    }

    public RoofThrowerLevel getLevel() {
        return level;
    }

    public RoofThrowerKnob() {
        type = ThrowerType.ROOF;
        level = RoofThrowerLevel.A;
    }

    @Override
    public void turnLeft() {
        switch (level) {
            case B -> level = RoofThrowerLevel.A;
            case C -> level = RoofThrowerLevel.B;
        }
    }

    @Override
    public void turnRight() {
        switch (level) {
            case A -> level = RoofThrowerLevel.B;
            case B -> level = RoofThrowerLevel.C;
        }
    }
}
