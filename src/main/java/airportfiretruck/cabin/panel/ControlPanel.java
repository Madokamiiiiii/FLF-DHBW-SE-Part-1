package airportfiretruck.cabin.panel;

import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.switches.PanelSwitch;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.panel.switches.Switch;
import airportfiretruck.centralunit.IControlPanelCentralUnit;

import java.util.ArrayList;
import java.util.List;

public class ControlPanel {

    private IControlPanelCentralUnit centralUnit;
    private List<IRotaryKnob> knobs;
    private List<Switch> switches;

    public void connectToCentralUnit(IControlPanelCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public void thrower(IRotaryKnob rotaryKnob) {
        centralUnit.thrower(rotaryKnob);
    }

    public void build() {
        knobs = new ArrayList<>();
        knobs.add(0, new FrontThrowerKnob());
        knobs.add(1, new RoofThrowerKnob());
        switches = new ArrayList<>();
        switches.add(0, new PanelSwitch(RelatedDevice.ENGINES));
        switches.add(1, new PanelSwitch(RelatedDevice.WARNING_LIGHTS));
        switches.add(2, new PanelSwitch(RelatedDevice.BLUE_LIGHTS));
        switches.add(3, new PanelSwitch(RelatedDevice.FRONT_LIGHTS));
        switches.add(4, new PanelSwitch(RelatedDevice.ROOF_LIGHTS));
        switches.add(5, new PanelSwitch(RelatedDevice.SIDE_LIGHTS));
        for (Switch panelSwitch : switches) {
            ((PanelSwitch) panelSwitch).connectToPanel(this);
        }
    }

    public void panelSwitch(RelatedDevice relatedDevice, boolean isOn) {
        centralUnit.panelSwitch(relatedDevice, isOn);
    }

    public IControlPanelCentralUnit getCentralUnit() {
        return centralUnit;
    }

    public List<IRotaryKnob> getKnobs() {
        return knobs;
    }

    public List<Switch> getSwitches() {
        return switches;
    }
}
