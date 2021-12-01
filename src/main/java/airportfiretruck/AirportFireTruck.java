package airportfiretruck;

import airportfiretruck.cabin.Cabin;
import airportfiretruck.centralunit.CentralUnit;
import airportfiretruck.engine.ElectroEngine;
import airportfiretruck.engine.IEngine;
import airportfiretruck.lights.*;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.LightSize;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.lights.position.FrontRearSide;
import airportfiretruck.lights.position.LeftRightSide;
import airportfiretruck.lights.position.Position;
import airportfiretruck.thrower.FloorSprayNozzle;
import airportfiretruck.thrower.FrontThrower;
import airportfiretruck.thrower.RoofThrower;
import airportfiretruck.wheels.Axle;
import airportfiretruck.wheels.FrontAxle;

import java.util.ArrayList;
import java.util.List;

public class AirportFireTruck {
    private final List<IEngine> engines;
    private final CentralUnit centralUnit;
    private final Cabin cabin;
    private final RoofThrower roofThrower;
    private final FrontThrower frontThrower;
    private List<FloorSprayNozzle> floorSprayNozzles;
    private List<Axle> axles;
    private List<FrontAxle> frontAxles;
    private List<DirectionIndicatorLight> directionIndicatorLights;
    private List<BrakeLight> brakeLights;
    private List<HeadLight> headLights;
    private List<SideLight> sideLights;
    private List<BlueLight> blueLights;
    private List<WarningLight> warningLights;
    private List<FrontLight> frontLights;

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

    public List<FrontLight> getFrontLights() {
        return frontLights;
    }

    public AirportFireTruck(Builder builder) {
        centralUnit = builder.centralUnit;
        centralUnit.setFlf(this);

        cabin = builder.cabin;
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

    public class Builder {
        private final CentralUnit centralUnit;
        private final Cabin cabin;
        private final RoofThrower roofThrower;
        private final FrontThrower frontThrower;
        private final List<IEngine> engines = new ArrayList<>();
        private final List<FloorSprayNozzle> floorSprayNozzles = new ArrayList<>();
        private final List<Axle> axles = new ArrayList<>();
        private final List<FrontAxle> frontAxles = new ArrayList<>();
        private final List<FrontLight> frontLights = new ArrayList<>();
        private final List<WarningLight> warningLights = new ArrayList<>();
        private final List<SideLight> sideLights = new ArrayList<>();
        private final List<HeadLight> headLights = new ArrayList<>();
        private final List<BrakeLight> brakeLights = new ArrayList<>();
        private final List<BlueLight> blueLights = new ArrayList<>();
        private final List<DirectionIndicatorLight> directionIndicatorLights = new ArrayList<>();

        public Builder() {
            centralUnit = new CentralUnit();
            cabin = new Cabin();
            roofThrower = new RoofThrower();
            frontThrower = new FrontThrower();
            for (int i = 0; i < 7; i++) {
                floorSprayNozzles.add(i, new FloorSprayNozzle());
            }
            for (int i = 0; i < 2; i++) {
                engines.add(i, new ElectroEngine());
                axles.add(i, new Axle());
                frontAxles.add(i, new FrontAxle());
                blueLights.add(i, new BlueLight());
                blueLights.get(i).setLightSize(LightSize.SMALL);
                blueLights.get(i).setFrontRearSide(FrontRearSide.FRONT);
                blueLights.add(i + 2, new BlueLight());
                blueLights.get(i + 2).setLightSize(LightSize.LARGE);
                blueLights.get(i + 2).setFrontRearSide(FrontRearSide.FRONT);
                blueLights.get(i + 2).setLeftRightSide(LeftRightSide.LEFT);
                blueLights.add(i + 4, new BlueLight());
                blueLights.get(i + 4).setLightSize(LightSize.LARGE);
                blueLights.get(i + 4).setFrontRearSide(FrontRearSide.FRONT);
                blueLights.get(i + 4).setLeftRightSide(LeftRightSide.RIGHT);
                blueLights.get(i + 6).setLightSize(LightSize.MEDIUM);
                blueLights.get(i + 6).setFrontRearSide(FrontRearSide.REAR);
                blueLights.get(i + 6).setLeftRightSide(LeftRightSide.LEFT);
                blueLights.add(i + 8, new BlueLight());
                blueLights.get(i + 8).setLightSize(LightSize.MEDIUM);
                blueLights.get(i + 8).setFrontRearSide(FrontRearSide.REAR);
                blueLights.get(i + 8).setLeftRightSide(LeftRightSide.RIGHT);
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

        public AirportFireTruck build() {
            return new AirportFireTruck(this);
        }
    }
}
