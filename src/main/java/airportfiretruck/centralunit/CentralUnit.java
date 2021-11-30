package airportfiretruck.centralunit;

import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.displays.VelocityDisplay;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.engine.Engine;
import airportfiretruck.engine.IEngine;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.position.LeftRightSide;

public class CentralUnit implements IPedalCentralUnit, ISteeringCentralUnit, IThrowerCentralUnit, IControlPanelCentralUnit{
    private AirportFireTruck flf;
    public CentralUnit() {

    }
    @Override
    public void pedal(PedalType ptype) {
        int sign = 4;
        if (ptype == PedalType.BRAKE) {
            sign = -4;
        }

        int newVelocity = ((VelocityDisplay) flf.getCabin().getDisplays().get(1)).getVelocity() + sign;
        if (newVelocity < 0) {
            return;
        }
        ((VelocityDisplay) flf.getCabin().getDisplays().get(1)).setVelocity(newVelocity);

        for (IEngine engine :flf.getEngine()) {
            engine.setVelocity(newVelocity);
            engine.rotate(newVelocity);
        }
        for (BrakeLight blight : flf.getBlights()) {
            if (sign < 0) {
                blight.on();
                continue;
            }
            blight.off();
        }
    }

    @Override
    public void pswitch(RelatedDevice device, boolean isOn) {

    }

    @Override
    public void steer() {
        int position = flf.getCabin().getSwheel().getPosition();
        flf.getFaxles().get(0).setSangle(position);
        flf.getFaxles().get(1).setSangle(position);
        if (position == 0) {
            for (DirectionIndicatorLight dlight: flf.getDlights()) {
                dlight.off();
            }
        }
        if (position < 0) {
            for (DirectionIndicatorLight dlight: flf.getDlights()) {
                if (dlight.getLrside() == LeftRightSide.LEFT) {
                    dlight.on();
                    continue;
                }
                dlight.off();
            }
        }
        if (position > 0) {
            for (DirectionIndicatorLight dlight: flf.getDlights()) {
                if (dlight.getLrside() == LeftRightSide.RIGHT) {
                    dlight.on();
                    continue;
                }
                dlight.off();
            }
        }
    }

    @Override
    public void throww(IRotaryKnob tknob) {

    }
}
