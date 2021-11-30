package airportfiretruck.centralunit;

import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;

public interface IControlPanelCentralUnit extends IBaseCentralUnit{
    public void pswitch(RelatedDevice device, boolean isOn);
    public void throww(IRotaryKnob tknob);
}
