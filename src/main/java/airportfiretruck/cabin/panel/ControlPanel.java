package airportfiretruck.cabin.panel;

import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.centralunit.IControlPanelCentralUnit;

public class ControlPanel {

    private IControlPanelCentralUnit centralUnit;

    public ControlPanel() {

    }

    public void thrower(IRotaryKnob rotaryKnob) {

    }

    public void build() {

    }

    public void panelSwitch(RelatedDevice relatedDevice, boolean isOn) {
        centralUnit.panelSwitch(relatedDevice, isOn);
    }
}
