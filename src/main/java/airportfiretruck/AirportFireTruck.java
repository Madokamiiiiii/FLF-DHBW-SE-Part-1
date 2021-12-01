package airportfiretruck;

import airportfiretruck.cabin.Cabin;
import airportfiretruck.centralunit.CentralUnit;
import airportfiretruck.engine.IEngine;
import airportfiretruck.lights.*;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.WarningLight;
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
        //          .
        //          .
        //          .
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

            // genaue Implementation für schlaflose Nächte
        }

        public AirportFireTruck build() {
            return new AirportFireTruck(this);
        }
    }
}
