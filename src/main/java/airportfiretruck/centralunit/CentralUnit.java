package airportfiretruck.centralunit;

import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.displays.VelocityDisplay;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.engine.IEngine;
import airportfiretruck.lights.*;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.lights.position.LeftRightSide;

public class CentralUnit implements IPedalCentralUnit, ISteeringCentralUnit, IThrowerCentralUnit, IControlPanelCentralUnit {
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

        for (IEngine engine : flf.getEngines()) {
            engine.setVelocity(newVelocity);
            engine.rotate(newVelocity);
        }
        for (BrakeLight blight : flf.getBrakeLights()) {
            if (sign < 0) {
                blight.on();
                continue;
            }
            blight.off();
        }
    }

    @Override
    public void panelSwitch(RelatedDevice device, boolean isOn) {
        switch (device) {
            case ENGINES -> {
                for (IEngine engine : flf.getEngines()) {
                    if (isOn) {
                        engine.off();
                        continue;
                    }
                    engine.on();
                }
            }
            case BLUE_LIGHTS -> {
                for (BlueLight bl : flf.getBlueLights()) {
                    if (isOn) {
                        bl.off();
                        continue;
                    }
                    bl.on();
                }
            }
            case ROOF_LIGHTS -> {
                for (HeadLight hl : flf.getHeadLights()) {
                    if (isOn) {
                        hl.off();
                        continue;
                    }
                    hl.on();
                }
            }
            case SIDE_LIGHTS -> {
                for (SideLight sl : flf.getSideLights()) {
                    if (isOn) {
                        sl.off();
                        continue;
                    }
                    sl.on();
                }
            }
            case FRONT_LIGHTS -> {
                for (FrontLight fl : flf.getFrontLights()) {
                    if (isOn) {
                        fl.off();
                        continue;
                    }
                    fl.on();
                }
            }
            case WARNING_LIGHTS -> {
                for (WarningLight wl : flf.getWarningLights()) {
                    if (isOn) {
                        wl.off();
                        continue;
                    }
                    wl.on();
                }
            }
        }
    }

    @Override
    public void steer() {
        int position = flf.getCabin().getSwheel().getPosition();
        flf.getFrontAxles().get(0).setSteeringAngle(position);
        flf.getFrontAxles().get(1).setSteeringAngle(position);
        if (position == 0) {
            for (DirectionIndicatorLight dlight : flf.getDirectionIndicatorLights()) {
                dlight.off();
            }
        }
        if (position < 0) {
            for (DirectionIndicatorLight dlight : flf.getDirectionIndicatorLights()) {
                if (dlight.getLeftRightSide() == LeftRightSide.LEFT) {
                    dlight.on();
                    continue;
                }
                dlight.off();
            }
        }
        if (position > 0) {
            for (DirectionIndicatorLight dlight : flf.getDirectionIndicatorLights()) {
                if (dlight.getLeftRightSide() == LeftRightSide.RIGHT) {
                    dlight.on();
                    continue;
                }
                dlight.off();
            }
        }
    }

    @Override
    public void thrower(IRotaryKnob tknob) {
        switch (tknob.getType()) {
            case ROOF -> flf.getRoofThrower().setLevel(((RoofThrowerKnob) tknob).getLevel());
            case FRONT -> flf.getFrontThrower().setLevel(((FrontThrowerKnob) tknob).getLevel());
        }
    }

    public void setFlf(AirportFireTruck flf) {
        this.flf = flf;
    }
}
