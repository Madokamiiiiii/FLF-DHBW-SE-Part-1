package airportfiretruck.centralunit;

import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.pedals.PedalType;

public class CentralUnit implements IPedalCentralUnit, ISteeringCentralUnit, IThrowerCentralUnit, IControlPanelCentralUnit{
    public CentralUnit() {

    }
    @Override
    public void pedal(PedalType ptype) {

    }

    @Override
    public void pswitch(RelatedDevice device, boolean isOn) {

    }

    @Override
    public void steer(int amount) {

    }

    @Override
    public void throww(IRotaryKnob tknob) {

    }
}
