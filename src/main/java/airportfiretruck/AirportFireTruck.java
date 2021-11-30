package airportfiretruck;

import airportfiretruck.cabin.Cabin;
import airportfiretruck.centralunit.CentralUnit;
import airportfiretruck.engine.ElectroEngine;
import airportfiretruck.engine.IEngine;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.HeadLight;
import airportfiretruck.lights.SideLight;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.lights.position.FrontLight;
import airportfiretruck.thrower.FloorSprayNozzle;
import airportfiretruck.thrower.FrontThrower;
import airportfiretruck.thrower.RoofThrower;
import airportfiretruck.wheels.Axle;
import airportfiretruck.wheels.FrontAxle;

import java.util.ArrayList;
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
    private List<FrontLight> flights;

    public Cabin getCabin() {
        return cabin;
    }

    public CentralUnit getCunit() {
        return cunit;
    }

    public RoofThrower getRthrower() {
        return rthrower;
    }

    public List<FrontAxle> getFaxles() {
        return faxles;
    }
    public List<DirectionIndicatorLight> getDlights() {
        return dlights;
    }
    public List<IEngine> getEngine() {
        return engine;
    }

    public List<BlueLight> getBulights() {
        return bulights;
    }

    public List<BrakeLight> getBlights() {
        return blights;
    }

    public List<HeadLight> getHlights() {
        return hlights;
    }

    public List<SideLight> getSlights() {
        return slights;
    }

    public List<WarningLight> getWlights() {
        return wlights;
    }

    public List<FrontLight> getFlights() {
        return flights;
    }

    public AirportFireTruck(Builder builder) {
        cunit = builder.cunit;
        //          .
        //          .
        //          .
    }

    public class Builder {
        private final CentralUnit cunit;
        private final Cabin cabin;
        private final RoofThrower rthrower;
        private final FrontThrower fthrower;
        private final List<IEngine> e = new ArrayList<>();
        private final List<FloorSprayNozzle> fnozzles = new ArrayList<>();
        private final List<Axle> axles = new ArrayList<>();
        private final List<FrontAxle> faxles = new ArrayList<>();
        private final List<FrontLight> fl = new ArrayList<>();
        private final List<WarningLight> wl = new ArrayList<>();
        private final List<SideLight> sl = new ArrayList<>();
        private final List<HeadLight> hl = new ArrayList<>();
        private final List<BrakeLight> bl = new ArrayList<>();
        private final List<BlueLight> bul = new ArrayList<>();
        private final List<DirectionIndicatorLight> dl = new ArrayList<>();

        public Builder() {
            cunit = new CentralUnit();
            cabin = new Cabin();
            rthrower = new RoofThrower();
            fthrower = new FrontThrower();

            // genaue Implementation für schlaflose Nächte
        }

        public AirportFireTruck build() {
            return new AirportFireTruck(this);
        }
    }
}
