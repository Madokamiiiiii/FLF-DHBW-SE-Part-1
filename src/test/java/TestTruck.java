import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.Cabin;
import airportfiretruck.cabin.SteeringWheel;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.cabin.seats.FrontSeat;
import airportfiretruck.cabin.seats.Seat;
import airportfiretruck.engine.IEngine;
import airportfiretruck.extinguisher.thrower.FloorSprayNozzle;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;
import airportfiretruck.extinguisher.watersupply.ExtinguishingAgent;
import airportfiretruck.extinguisher.watersupply.Tank;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.HeadLight;
import airportfiretruck.lights.SideLight;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.LightSize;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.wheels.Axle;
import airportfiretruck.wheels.FrontAxle;
import airportfiretruck.wheels.RearAxle;
import airportfiretruck.wheels.Wheel;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static airportfiretruck.position.FrontRearSide.FRONT;
import static airportfiretruck.position.FrontRearSide.REAR;
import static airportfiretruck.position.LeftRightSide.*;
import static airportfiretruck.position.Position.BOTTOM;
import static airportfiretruck.position.Position.TOP;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTruck {

    private AirportFireTruck airportFireTruck;

    @BeforeEach
    public void init() {
        // Generate FLF
        airportFireTruck = new AirportFireTruck.Builder().build();
    }

    @Order(1)
    @Test
    public void buildComplete() {
        // Test Lights

        // Test Headlights
        List<HeadLight> headLight = airportFireTruck.getHeadLights();
        assertEquals(10, headLight.size());
        assertEquals(4, Helper.testLightPosition(headLight, TOP, MIDDLE, FRONT));
        assertEquals(3, Helper.testLightPosition(headLight, BOTTOM, RIGHT, FRONT));
        assertEquals(3, Helper.testLightPosition(headLight, BOTTOM, LEFT, FRONT));

        // Test Direction Indicator
        List<DirectionIndicatorLight> directionIndicatorLights = airportFireTruck.getDirectionIndicatorLights();
        assertEquals(4, directionIndicatorLights.size());
        assertEquals(1, Helper.testLightPosition(directionIndicatorLights, null, RIGHT, FRONT));
        assertEquals(1, Helper.testLightPosition(directionIndicatorLights, null, LEFT, FRONT));
        assertEquals(1, Helper.testLightPosition(directionIndicatorLights, null, RIGHT, REAR));
        assertEquals(1, Helper.testLightPosition(directionIndicatorLights, null, LEFT, REAR));

        // Test Brake Lights
        List<BrakeLight> brakeLights = airportFireTruck.getBrakeLights();
        assertEquals(2, brakeLights.size());
        assertEquals(1, Helper.testLightPosition(brakeLights, null, RIGHT, REAR));
        assertEquals(1, Helper.testLightPosition(brakeLights, null, LEFT, REAR));

        // Test SideLights
        List<SideLight> sideLights = airportFireTruck.getSideLights();
        assertEquals(10, sideLights.size());
        assertEquals(5, Helper.testLightPosition(sideLights, null, RIGHT, null));
        assertEquals(5, Helper.testLightPosition(sideLights, null, LEFT, null));

        // Test BlueLights
        List<BlueLight> blueLights = airportFireTruck.getBlueLights();
        assertEquals(10, blueLights.size());
        assertEquals(1, Helper.testLedLightAndPosition(blueLights, BOTTOM, LEFT, FRONT, LightSize.SMALL));
        assertEquals(1, Helper.testLedLightAndPosition(blueLights, BOTTOM, RIGHT, FRONT, LightSize.SMALL));
        assertEquals(2, Helper.testLedLightAndPosition(blueLights, TOP, LEFT, FRONT, LightSize.LARGE));
        assertEquals(2, Helper.testLedLightAndPosition(blueLights, TOP, RIGHT, FRONT, LightSize.LARGE));
        assertEquals(2, Helper.testLedLightAndPosition(blueLights, BOTTOM, LEFT, REAR, LightSize.MEDIUM));
        assertEquals(2, Helper.testLedLightAndPosition(blueLights, BOTTOM, RIGHT, REAR, LightSize.MEDIUM));

        // Test WarningLights
        List<WarningLight> warningLights = airportFireTruck.getWarningLights();
        assertEquals(2, warningLights.size());
        assertEquals(1, Helper.testLedLightAndPosition(warningLights, TOP, LEFT, FRONT));
        assertEquals(1, Helper.testLedLightAndPosition(warningLights, TOP, RIGHT, REAR));

        // Test Cabin
        Cabin cabin = airportFireTruck.getCabin();

        assertEquals(2, cabin.getDoors().size());
        cabin.getDoors().forEach(busDoor -> assertEquals(2, busDoor.getButtons().size()));

        List<Seat> seats = cabin.getSeats();
        assertEquals(4, seats.size());
        assertEquals(2, seats.stream().filter(seat -> seat instanceof FrontSeat).count());
        seats.forEach(seat -> assertNotNull(seat.getRespirator()));

        IJoystick joystick = cabin.getFrontThrowerJoystick();
        assertNotNull(joystick);
        assertTrue(joystick instanceof FrontThrowerJoystick);
        FrontThrowerJoystick frontThrowerJoystick = (FrontThrowerJoystick) joystick;
        assertNotNull(frontThrowerJoystick.getJoystickButton());
        assertEquals(2, frontThrowerJoystick.getPushButtons().size());

        joystick = cabin.getRoofThrowerJoystick();
        assertNotNull(joystick);
        assertTrue(joystick instanceof RoofThrowerJoystick);
        RoofThrowerJoystick roofThrowerJoystick = (RoofThrowerJoystick) joystick;
        assertNotNull(roofThrowerJoystick.getJoystickButton());
        assertEquals(2, roofThrowerJoystick.getPushButtons().size());

        assertNotNull(cabin.getSteeringWheel());
        assertNotNull(cabin.getSteeringWheel().getCentralUnit());

        // Test ControlPanel
        ControlPanel panel = cabin.getControlPanel();
        assertEquals(6, panel.getSwitches().size());
        assertEquals(2, panel.getKnobs().size());
        assertNotNull(panel.getCentralUnit());

        SteeringWheel steeringWheel = cabin.getSteeringWheel();
        assertNotNull(steeringWheel);
        assertNotNull(steeringWheel.getCentralUnit());

        assertEquals(2, cabin.getDisplays().size());

        List<Pedal> pedals = cabin.getPedals();
        List<Pedal> gas = pedals.stream().filter(pedal -> pedal.getPedalType().equals(PedalType.GAS)).collect(Collectors.toList());
        assertEquals(1, gas.size());
        assertNotNull(gas.get(0).getCentralUnit());
        List<Pedal> brake = pedals.stream().filter(pedal -> pedal.getPedalType().equals(PedalType.BRAKE)).collect(Collectors.toList());
        assertEquals(1, brake.size());
        assertNotNull(brake.get(0).getCentralUnit());

        // Test extinguisher
        // Test Thrower
        FrontThrower frontThrower = airportFireTruck.getFrontThrower();
        assertEquals(frontThrowerJoystick.getThrower(), frontThrower);
        assertNotNull(frontThrower.getMixer());

        RoofThrower roofThrower = airportFireTruck.getRoofThrower();
        assertEquals(roofThrowerJoystick.getThrower(), roofThrower);
        assertNotNull(roofThrower.getMixer());
        assertNotNull(roofThrower.getLowerSegment());
        assertNotNull(roofThrower.getUpperSegment());
        assertEquals(3, roofThrower.getUpperSegment().getSegmentParts().size());

        List<FloorSprayNozzle> floorSprayNozzles = airportFireTruck.getFloorSprayNozzles();
        assertEquals(7, floorSprayNozzles.size());
        floorSprayNozzles.forEach(floorSprayNozzle -> assertNotNull(floorSprayNozzle.getTank()));

        // Test Watersupply
        List<Tank> tanks = roofThrower.getMixer().getTanks();
        assertEquals(2, tanks.size());
        assertEquals(1, tanks.stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).count());
        assertEquals(1, tanks.stream().filter(tank -> tank.getType() == ExtinguishingAgent.FOAM).count());

        // Test Axles
        List<FrontAxle> frontAxles = airportFireTruck.getFrontAxles();
        assertEquals(2, frontAxles.size());
        List<RearAxle> rearAxles = airportFireTruck.getRearAxles();
        assertEquals(2, rearAxles.size());

        List<? extends Axle> axles = Stream.concat(frontAxles.stream(), rearAxles.stream()).toList();
        axles.forEach(axle -> {
            List<Wheel> wheels = axle.getWheels();
            assertEquals(2, wheels.size());
            wheels.forEach(wheel -> {
                assertNotNull(wheel.getTire());
                assertEquals(3, wheel.getBrakeDisks().size());
            });
        });

        // Test Engine
        List<IEngine> engines = airportFireTruck.getEngines();
        assertEquals(2, engines.size());
        engines.forEach(engine -> assertNotNull(engine.getBatteryManagement()));

        // TODO: Test Battery?

        // Test CentralUnit
        assertNotNull(airportFireTruck.getCentralUnit());
    }

    @Test
    @Order(2)
    public void usageControlPanel() {

    }

    @Test
    @Order(3)
    public void handleParking() {

    }

    @Test
    @Order(4)
    public void handleInspectionDrive() {

    }

    @Test
    @Order(5)
    public void handleEmergencyDrive() {

    }

    @Test
    @Order(6)
    public void handleFuelTruckOnFire() {

    }

    @Test
    @Order(7)
    public void handlePushbackVehicleOnFire() {

    }

    @Test
    @Order(8)
    public void handleAirplaneEngineFire() {

    }

}
