package airportfiretruck;

import airportfiretruck.cabin.Cabin;
import airportfiretruck.centralunit.CentralUnit;
import airportfiretruck.engine.ElectroEngine;
import airportfiretruck.engine.IEngine;
import airportfiretruck.extinguisher.thrower.FloorSprayNozzle;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.extinguisher.thrower.roof.LowerSegment;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;
import airportfiretruck.extinguisher.thrower.roof.UpperSegment;
import airportfiretruck.extinguisher.watersupply.ExtinguishingAgent;
import airportfiretruck.extinguisher.watersupply.Mixer;
import airportfiretruck.extinguisher.watersupply.Tank;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.HeadLight;
import airportfiretruck.lights.SideLight;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.LightSize;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;
import airportfiretruck.wheels.*;

import java.util.ArrayList;
import java.util.List;

public class AirportFireTruck {
    private final List<IEngine> engines;
    private final CentralUnit centralUnit;
    private final Cabin cabin;
    private final RoofThrower roofThrower;
    private final FrontThrower frontThrower;
    private final List<FloorSprayNozzle> floorSprayNozzles;
    private final List<Axle> axles;
    private final List<FrontAxle> frontAxles;
    private final List<DirectionIndicatorLight> directionIndicatorLights;
    private final List<BrakeLight> brakeLights;
    private final List<HeadLight> headLights;
    private final List<SideLight> sideLights;
    private final List<BlueLight> blueLights;
    private final List<WarningLight> warningLights;

    public Cabin getCabin() {
        return cabin;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public RoofThrower getRoofThrower() {
        return roofThrower;
    }

    public FrontThrower getFrontThrower() {
        return frontThrower;
    }

    public List<FrontAxle> getFrontAxles() {
        return frontAxles;
    }

    public List<DirectionIndicatorLight> getDirectionIndicatorLights() {
        return directionIndicatorLights;
    }

    public List<IEngine> getEngines() {
        return engines;
    }

    public List<BlueLight> getBlueLights() {
        return blueLights;
    }

    public List<BrakeLight> getBrakeLights() {
        return brakeLights;
    }

    public List<HeadLight> getHeadLights() {
        return headLights;
    }

    public List<SideLight> getSideLights() {
        return sideLights;
    }

    public List<WarningLight> getWarningLights() {
        return warningLights;
    }

    public AirportFireTruck(Builder builder) {
        centralUnit = builder.centralUnit;
        centralUnit.setFlf(this);

        cabin = builder.cabin;
        cabin.connectToCentralUnit(centralUnit);
        engines = builder.engines;
        roofThrower = builder.roofThrower;
        frontThrower = builder.frontThrower;
        floorSprayNozzles = builder.floorSprayNozzles;
        axles = builder.axles;
        frontAxles = builder.frontAxles;
        directionIndicatorLights = builder.directionIndicatorLights;
        sideLights = builder.sideLights;
        warningLights = builder.warningLights;
        blueLights = builder.blueLights;
        brakeLights = builder.brakeLights;
        headLights = builder.headLights;
    }

    public List<FloorSprayNozzle> getFloorSprayNozzles() {
        return floorSprayNozzles;
    }

    public static class Builder {
        private final CentralUnit centralUnit;
        private final Cabin cabin;
        private final RoofThrower roofThrower;
        private final FrontThrower frontThrower;
        private final List<IEngine> engines = new ArrayList<>();
        private final List<FloorSprayNozzle> floorSprayNozzles = new ArrayList<>();
        private final List<Axle> axles = new ArrayList<>();
        private final List<FrontAxle> frontAxles = new ArrayList<>();
        private final List<WarningLight> warningLights = new ArrayList<>();
        private final List<SideLight> sideLights = new ArrayList<>();
        private final List<HeadLight> headLights = new ArrayList<>();
        private final List<BrakeLight> brakeLights = new ArrayList<>();
        private final List<BlueLight> blueLights = new ArrayList<>();
        private final List<DirectionIndicatorLight> directionIndicatorLights = new ArrayList<>();

        public Builder() {
            centralUnit = new CentralUnit();
            cabin = new Cabin();
            cabin.build();

            // Thrower
            Tank waterTank = new Tank(ExtinguishingAgent.WATER);
            Tank foamTank = new Tank(ExtinguishingAgent.FOAM);
            Mixer mixer = new Mixer(List.of(waterTank, foamTank));
            roofThrower = new RoofThrower(cabin.getRoofThrowerJoystick(), mixer, 10000, new UpperSegment(), new LowerSegment());
            frontThrower = new FrontThrower(cabin.getFrontThrowerJoystick(), mixer, 3500);
            for (int i = 0; i < 7; i++) {
                floorSprayNozzles.add(i, new FloorSprayNozzle(100, waterTank));
            }

            for (int i = 0; i < 2; i++) {
                engines.add(i, new ElectroEngine());
                axles.add(i, new Axle());
                frontAxles.add(i, new FrontAxle());
                blueLights.add(i, new BlueLight(LightSize.SMALL));
                blueLights.get(i).setFrontRearSide(FrontRearSide.FRONT);
                directionIndicatorLights.add(i, new DirectionIndicatorLight());
                directionIndicatorLights.add(i + 2, new DirectionIndicatorLight());
                directionIndicatorLights.get(i).setLeftRightSide(LeftRightSide.LEFT);
                directionIndicatorLights.get(2 * i).setFrontRearSide(FrontRearSide.FRONT);
                directionIndicatorLights.get(i + 2).setLeftRightSide(LeftRightSide.RIGHT);
                directionIndicatorLights.get(2 * i + 1).setFrontRearSide(FrontRearSide.REAR);
                brakeLights.add(i, new BrakeLight());
                brakeLights.get(i).setFrontRearSide(FrontRearSide.REAR);
                warningLights.add(i, new WarningLight());
                warningLights.get(i).setPosition(Position.TOP);
                headLights.add(i, new HeadLight());
                headLights.add(i + 2, new HeadLight());
                headLights.get(i).setPosition(Position.TOP);
                headLights.get(i).setFrontRearSide(FrontRearSide.FRONT);
                headLights.get(i + 2).setPosition(Position.TOP);
                headLights.get(i + 2).setFrontRearSide(FrontRearSide.FRONT);
            }
            for (int i = 0; i < 3; i++) {
                sideLights.add(i, new SideLight());
                sideLights.add(i + 3, new SideLight());
                sideLights.get(i).setLeftRightSide(LeftRightSide.LEFT);
                sideLights.get(i).setFrontRearSide(FrontRearSide.FRONT);
                sideLights.get(i + 3).setLeftRightSide(LeftRightSide.RIGHT);
                sideLights.get(i + 3).setFrontRearSide(FrontRearSide.FRONT);
            }

            warningLights.get(0).setFrontRearSide(FrontRearSide.REAR);
            warningLights.get(0).setLeftRightSide(LeftRightSide.RIGHT);
            warningLights.get(1).setFrontRearSide(FrontRearSide.FRONT);
            warningLights.get(1).setLeftRightSide(LeftRightSide.LEFT);
            brakeLights.get(0).setLeftRightSide(LeftRightSide.LEFT);
            brakeLights.get(1).setLeftRightSide(LeftRightSide.RIGHT);
        }

        private void buildAxle(Axle axle) {
            List<Wheel> wheels = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                List<BrakeDisk> brakeDisks = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    brakeDisks.add(j, new BrakeDisk());
                }
                Tire tire = new Tire();
                Wheel wheel = new Wheel();
                wheel.setTire(tire);
                wheel.setBrakeDisks(brakeDisks);
                wheels.add(i, wheel);
            }
            axle.setWheels(wheels);
        }

        public AirportFireTruck build() {
            return new AirportFireTruck(this);
        }
    }
}
