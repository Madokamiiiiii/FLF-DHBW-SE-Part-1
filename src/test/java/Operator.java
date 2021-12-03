import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.panel.switches.PanelSwitch;
import airportfiretruck.cabin.panel.switches.RelatedDevice;

import java.util.List;

// This simulates the operator in the tests
public class Operator {
    private final List<PanelSwitch> switches;
    private final FrontThrowerKnob frontThrowerKnob;
    private final RoofThrowerKnob roofThrowerKnob;

    public Operator(ControlPanel panel) {
        switches = panel.getSwitches();
        frontThrowerKnob = (FrontThrowerKnob) panel.getKnobs().stream().filter(knob -> knob.getType() == ThrowerType.FRONT).findFirst().orElseThrow(IllegalArgumentException::new);
        roofThrowerKnob = (RoofThrowerKnob) panel.getKnobs().stream().filter(knob -> knob.getType() == ThrowerType.ROOF).findFirst().orElseThrow(IllegalArgumentException::new);

    }

    public void switchOn(RelatedDevice device) {
        PanelSwitch panelSwitch = switches.stream().filter(singleSwitch -> singleSwitch.getDevice() == device).findAny().orElseThrow(IllegalArgumentException::new);
        panelSwitch.pressed();
    }

    public void turnKnob(ThrowerType type, Direction direction) {
        switch (type) {
            case FRONT -> {
                if (direction == Direction.LEFT) {
                    frontThrowerKnob.turnLeft();
                } else {
                    frontThrowerKnob.turnRight();
                }
            }
            case ROOF -> {
                if (direction == Direction.LEFT) {
                    roofThrowerKnob.turnLeft();
                } else {
                    roofThrowerKnob.turnRight();
                }
            }
        }
    }
}
