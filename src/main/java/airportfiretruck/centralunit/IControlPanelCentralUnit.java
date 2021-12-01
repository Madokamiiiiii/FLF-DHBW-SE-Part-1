package airportfiretruck.centralunit;

import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;

public interface IControlPanelCentralUnit extends IBaseCentralUnit {
    void panelSwitch(RelatedDevice device, boolean isOn);

    void thrower(IRotaryKnob rotaryKnob);
}
