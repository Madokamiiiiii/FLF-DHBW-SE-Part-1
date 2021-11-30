package airportfiretruck.cabin.panel;

import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.centralunit.IControlPanelCentralUnit;

public class ControlPanel {
    private IControlPanelCentralUnit cunit;
    public ControlPanel() {

    }
    public void throww (IRotaryKnob tknob) {

    }
    public void build() {

    }
    public void pswitch(RelatedDevice rdevice, boolean isOn) {
        cunit.pswitch(rdevice, isOn);
    }
}
