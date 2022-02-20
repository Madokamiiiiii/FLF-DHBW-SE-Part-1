package airportfiretruck.centralunit;

import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.engine.IEngine;
import airportfiretruck.extinguisher.thrower.FloorSprayNozzle;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.HeadLight;
import airportfiretruck.lights.SideLight;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;

public class CentralUnit implements IPedalCentralUnit, ISteeringCentralUnit, IThrowerCentralUnit, IControlPanelCentralUnit {
    private AirportFireTruck flf;

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

        for (IEngine engine : flf.getEngines()) {
            engine.setVelocity(newVelocity);
            engine.rotate();
        }

        for (BrakeLight brakeLight : flf.getBrakeLights()) {
            if (sign < 0) {
                brakeLight.on();
            } else {
                brakeLight.off();
            }
        }
    }

    @Override
    public void panelSwitch(RelatedDevice device, boolean isOn) {
        switch (device) {
            case ENGINES -> {
                for (IEngine engine : flf.getEngines()) {
                    if (isOn) {
                        engine.on();
                        continue;
                    }
                    engine.off();
                }
            }
            case BLUE_LIGHTS -> {
                for (BlueLight blueLight : flf.getBlueLights()) {
                    if (isOn) {
                        blueLight.on();
                        continue;
                    }
                    blueLight.off();
                }
            }
            case ROOF_LIGHTS -> {
                for (HeadLight headLight : flf.getHeadLights()) {
                    if (headLight.getPosition() == Position.TOP) {
                        if (isOn) {
                            headLight.on();
                            continue;
                        }
                        headLight.off();
                    }
                }
            }
            case SIDE_LIGHTS -> {
                for (SideLight sideLight : flf.getSideLights()) {
                    if (isOn) {
                        sideLight.on();
                        continue;
                    }
                    sideLight.off();
                }
            }
            case FRONT_LIGHTS -> {
                for (HeadLight headLight : flf.getHeadLights()) {
                    if (headLight.getPosition() == Position.BOTTOM) {
                        if (isOn) {
                            headLight.on();
                            continue;
                        }
                        headLight.off();
                    }
                }
            }
            case WARNING_LIGHTS -> {
                for (WarningLight warningLight : flf.getWarningLights()) {
                    if (isOn) {
                        warningLight.on();
                        continue;
                    }
                    warningLight.off();
                }
            }
            case SELF_PROTECTION -> {
                for (FloorSprayNozzle floorSprayNozzle : flf.getFloorSprayNozzles()) {
                    floorSprayNozzle.setOn(isOn);
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
