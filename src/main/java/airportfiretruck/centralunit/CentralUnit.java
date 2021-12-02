package airportfiretruck.centralunit;

import airportfiretruck.AirportFireTruck;
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
    public void pedal(PedalType pedalType) {
        int sign = 4;
        if (pedalType == PedalType.BRAKE) {
            sign = -4;
        }

        int newVelocity = flf.getEngines().get(0).getVelocity() + sign;
        if (newVelocity < 0) { // Nicht rückwarts fahren
            return;
        }
        flf.getCabin().getDisplays().get(1).setValue(newVelocity);

        for (IEngine engine : flf.getEngines()) {
            engine.setVelocity(newVelocity);
            engine.rotate(newVelocity);
            flf.getCabin().getDisplays().get(0).setValue(engine.getBatteryManagement().getRemainingBatteryLevel());
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
                for (BlueLight blueLight : flf.getBlueLights()) {
                    if (isOn) {
                        blueLight.off();
                        continue;
                    }
                    blueLight.on();
                }
            }
            case ROOF_LIGHTS -> {
                for (HeadLight headLight : flf.getHeadLights()) {
                    if (isOn) {
                        headLight.off();
                        continue;
                    }
                    headLight.on();
                }
            }
            case SIDE_LIGHTS -> {
                for (SideLight sideLight : flf.getSideLights()) {
                    if (isOn) {
                        sideLight.off();
                        continue;
                    }
                    sideLight.on();
                }
            }
            case FRONT_LIGHTS -> {
                for (FrontLight frontLight : flf.getFrontLights()) {
                    if (isOn) {
                        frontLight.off();
                        continue;
                    }
                    frontLight.on();
                }
            }
            case WARNING_LIGHTS -> {
                for (WarningLight warningLight : flf.getWarningLights()) {
                    if (isOn) {
                        warningLight.off();
                        continue;
                    }
                    warningLight.on();
                }
            }
        }
    }

    @Override
    public void steer() {
        int position = flf.getCabin().getSteeringWheel().getPosition();
        flf.getFrontAxles().get(0).setSteeringAngle(position);
        flf.getFrontAxles().get(1).setSteeringAngle(position);
        if (position == 0) {
            for (DirectionIndicatorLight directionIndicatorLight : flf.getDirectionIndicatorLights()) {
                directionIndicatorLight.off();
            }
        }
        if (position < 0) {
            for (DirectionIndicatorLight directionIndicatorLight : flf.getDirectionIndicatorLights()) {
                if (directionIndicatorLight.getLeftRightSide() == LeftRightSide.LEFT) {
                    directionIndicatorLight.on();
                    continue;
                }
                directionIndicatorLight.off();
            }
        }
        if (position > 0) {
            for (DirectionIndicatorLight directionIndicatorLight : flf.getDirectionIndicatorLights()) {
                if (directionIndicatorLight.getLeftRightSide() == LeftRightSide.RIGHT) {
                    directionIndicatorLight.on();
                    continue;
                }
                directionIndicatorLight.off();
            }
        }
    }

    @Override
    public void thrower(IRotaryKnob rotaryKnob) {
        switch (rotaryKnob.getType()) {
            case ROOF -> flf.getRoofThrower().setLevel(((RoofThrowerKnob) rotaryKnob).getLevel());
            case FRONT -> flf.getFrontThrower().setLevel(((FrontThrowerKnob) rotaryKnob).getLevel());
        }
    }

    public void setFlf(AirportFireTruck flf) {
        this.flf = flf;
    }
}
