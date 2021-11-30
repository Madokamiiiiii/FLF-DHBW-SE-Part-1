package airportfiretruck;

import airportfiretruck.cabin.Cabin;
import airportfiretruck.centralunit.CentralUnit;
import airportfiretruck.engine.IEngine;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.HeadLight;
import airportfiretruck.lights.SideLight;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.thrower.FloorSprayNozzle;
import airportfiretruck.thrower.FrontThrower;
import airportfiretruck.thrower.RoofThrower;
import airportfiretruck.wheels.Axle;
import airportfiretruck.wheels.FrontAxle;

import java.util.List;

public class AirportFireTruck {
    private List<IEngine> engine;
    private CentralUnit cunit;
    private Cabin cabin;
    private RoofThrower rthrower;
    private FrontThrower fthrower;
    private List<FloorSprayNozzle> fnozzles;
    private List<Axle> axles;
    private List<FrontAxle> faxles;
    private List<DirectionIndicatorLight> dlights;
    private List<BrakeLight> blights;
    private List<HeadLight> hlights;
    private List<SideLight> slights;
    private List<BlueLight> bulights;
    private List<WarningLight> wlights;

    public AirportFireTruck(Builder builder) {

    }

    public class Builder {
        public AirportFireTruck build() {
            return null;
        }
    }
}
