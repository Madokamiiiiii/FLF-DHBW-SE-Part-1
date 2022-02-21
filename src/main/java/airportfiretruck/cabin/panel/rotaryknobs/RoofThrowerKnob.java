package airportfiretruck.cabin.panel.rotaryknobs;

import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.extinguisher.thrower.roof.RoofThrowerLevel;

public class RoofThrowerKnob extends RotaryKnob {

    private final ThrowerType type;
    private RoofThrowerLevel level;

    public RoofThrowerKnob(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        type = ThrowerType.ROOF;
        level = RoofThrowerLevel.A;
    }

    @Override
    public ThrowerType getType() {
        return type;
    }

    public RoofThrowerLevel getLevel() {
        return level;
    }

    @Override
    public void turnLeft() {
        switch (level) {
            case B -> level = RoofThrowerLevel.A;
            case C -> level = RoofThrowerLevel.B;
        }
        controlPanel.thrower(this);
    }

    @Override
    public void turnRight() {
        switch (level) {
            case A -> level = RoofThrowerLevel.B;
            case B -> level = RoofThrowerLevel.C;
        }
        controlPanel.thrower(this);
    }
}
