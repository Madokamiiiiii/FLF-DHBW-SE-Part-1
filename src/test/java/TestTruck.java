import Utils.*;
import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.Cabin;
import airportfiretruck.cabin.SteeringWheel;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.ThrowerType;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.cabin.seats.FrontSeat;
import airportfiretruck.cabin.seats.Seat;
import airportfiretruck.engine.ElectroEngine;
import airportfiretruck.engine.IEngine;
import airportfiretruck.engine.battery.BatteryManagement;
import airportfiretruck.extinguisher.thrower.FloorSprayNozzle;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.extinguisher.thrower.roof.RoofThrower;
import airportfiretruck.extinguisher.thrower.roof.RoofThrowerLevel;
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
import java.util.stream.Stream;

import static airportfiretruck.extinguisher.thrower.roof.RoofThrowerLevel.C;
import static airportfiretruck.position.FrontRearSide.FRONT;
import static airportfiretruck.position.FrontRearSide.REAR;
import static airportfiretruck.position.LeftRightSide.*;
import static airportfiretruck.position.Position.BOTTOM;
import static airportfiretruck.position.Position.TOP;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestTruck {

    private AirportFireTruck airportFireTruck;
    private Driver driver;
    private Operator operator;

    @BeforeEach
    public void init() {
        // Generate FLF
        airportFireTruck = new AirportFireTruck.Builder().build();
    }

    @Order(1)
    @Test
    void buildComplete() {
        // Test Lights

        // Test Headlights
        List<HeadLight> headLight = airportFireTruck.getHeadLights();
        assertEquals(10, headLight.size());
        assertEquals(4, Helper.testLightPosition(headLight, TOP, MIDDLE, FRONT));
        assertEquals(3, Helper.testLightPosition(headLight, BOTTOM, RIGHT, FRONT));
        assertEquals(3, Helper.testLightPosition(headLight, BOTTOM, LEFT, FRONT));

        // Direction Indicator
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

        IJoystick<FrontThrower> fJoystick = cabin.getFrontThrowerJoystick();
        assertNotNull(fJoystick);
        assertTrue(fJoystick instanceof FrontThrowerJoystick);
        FrontThrowerJoystick frontThrowerJoystick = (FrontThrowerJoystick) fJoystick;
        assertNotNull(frontThrowerJoystick.getJoystickButton());
        assertEquals(2, frontThrowerJoystick.getPushButtons().size());

        IJoystick<RoofThrower> rJoystick = cabin.getRoofThrowerJoystick();
        assertNotNull(rJoystick);
        assertTrue(rJoystick instanceof RoofThrowerJoystick);
        RoofThrowerJoystick roofThrowerJoystick = (RoofThrowerJoystick) rJoystick;
        assertNotNull(roofThrowerJoystick.getJoystickButton());
        assertEquals(2, roofThrowerJoystick.getPushButtons().size());

        assertNotNull(cabin.getSteeringWheel());
        assertNotNull(cabin.getSteeringWheel().getCentralUnit());

        // Test ControlPanel
        ControlPanel panel = cabin.getControlPanel();
        assertEquals(7, panel.getSwitches().size());
        assertEquals(2, panel.getKnobs().size());
        assertNotNull(panel.getCentralUnit());

        SteeringWheel steeringWheel = cabin.getSteeringWheel();
        assertNotNull(steeringWheel);
        assertNotNull(steeringWheel.getCentralUnit());

        assertEquals(2, cabin.getDisplays().size());

        List<Pedal> pedals = cabin.getPedals();
        List<Pedal> gas = pedals.stream().filter(pedal -> pedal.getPedalType().equals(PedalType.GAS)).toList();
        assertEquals(1, gas.size());
        assertNotNull(gas.get(0).getCentralUnit());
        List<Pedal> brake = pedals.stream().filter(pedal -> pedal.getPedalType().equals(PedalType.BRAKE)).toList();
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

        // Test Water supply
        List<Tank> tanks = roofThrower.getMixer().getTanks(); // It's not important if from roofThrower or frontThrower. They share the same tanks.
        assertEquals(2, tanks.size());
        assertEquals(1, tanks.stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).count());
        assertEquals(1, tanks.stream().filter(tank -> tank.getType() == ExtinguishingAgent.FOAM).count());

        // Test Axles
        List<FrontAxle> frontAxles = airportFireTruck.getFrontAxles();
        assertEquals(2, frontAxles.size());
        assertEquals(2, frontAxles.stream().filter(frontAxle -> frontAxle.getFrontRearSide() == FRONT).count());
        assertEquals(1, frontAxles.stream().filter(frontAxle -> frontAxle.getSide() == LEFT).count());
        assertEquals(1, frontAxles.stream().filter(frontAxle -> frontAxle.getSide() == RIGHT).count());
        List<RearAxle> rearAxles = airportFireTruck.getRearAxles();
        assertEquals(2, rearAxles.size());
        assertEquals(2, rearAxles.stream().filter(rearAxle -> rearAxle.getFrontRearSide() == REAR).count());
        assertEquals(1, rearAxles.stream().filter(rearAxle -> rearAxle.getSide() == LEFT).count());
        assertEquals(1, rearAxles.stream().filter(rearAxle -> rearAxle.getSide() == RIGHT).count());

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
        engines.forEach(engine -> assertNotNull(((ElectroEngine) engine).getBatteryManagement()));

        // Test Battery
        assertEquals(0, BatteryManagement.INSTANCE.getRemainingBatteryLevel());

        // Test CentralUnit
        assertNotNull(airportFireTruck.getCentralUnit());
    }

    @Test
    @Order(2)
    void usageControlPanel() {
        generateHelper();

        // Engines
        airportFireTruck.getEngines().forEach(engine -> assertFalse(engine.isOn()));
        operator.useSwitch(RelatedDevice.ENGINES);
        airportFireTruck.getEngines().forEach(engine -> assertTrue(engine.isOn()));

        // Warning Lights
        airportFireTruck.getWarningLights().forEach(warningLight -> assertFalse(warningLight.isOn()));
        operator.useSwitch(RelatedDevice.WARNING_LIGHTS);
        airportFireTruck.getWarningLights().forEach(warningLight -> assertTrue(warningLight.isOn()));

        // Blue Lights
        airportFireTruck.getBlueLights().forEach(blueLight -> assertFalse(blueLight.isOn()));
        operator.useSwitch(RelatedDevice.BLUE_LIGHTS);
        airportFireTruck.getBlueLights().forEach(blueLight -> assertTrue(blueLight.isOn()));

        // Front Lights
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertFalse(headLight.isOn()));
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(BOTTOM)).forEach(headLight -> assertFalse(headLight.isOn()));
        operator.useSwitch(RelatedDevice.FRONT_LIGHTS);
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertFalse(headLight.isOn())); // Should stay off
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(BOTTOM)).forEach(headLight -> assertTrue(headLight.isOn()));

        // Roof Lights
        // Turn lights off again
        operator.useSwitch(RelatedDevice.FRONT_LIGHTS);

        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertFalse(headLight.isOn()));
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(BOTTOM)).forEach(headLight -> assertFalse(headLight.isOn()));
        operator.useSwitch(RelatedDevice.ROOF_LIGHTS);
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertTrue(headLight.isOn()));
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(BOTTOM)).forEach(headLight -> assertFalse(headLight.isOn())); // Should stay off

        // Side Lights
        airportFireTruck.getSideLights().forEach(sideLight -> assertFalse(sideLight.isOn()));
        operator.useSwitch(RelatedDevice.SIDE_LIGHTS);
        airportFireTruck.getSideLights().forEach(sideLight -> assertTrue(sideLight.isOn()));

        // Self Protection
        airportFireTruck.getFloorSprayNozzles().forEach(floorSprayNozzle -> assertFalse(floorSprayNozzle.isOn()));
        operator.useSwitch(RelatedDevice.SELF_PROTECTION);
        airportFireTruck.getFloorSprayNozzles().forEach(floorSprayNozzle -> assertTrue(floorSprayNozzle.isOn()));
    }

    @Test
    @Order(3)
    void handleParking() {
        generateHelper();

        // s0101
        airportFireTruck.getEngines().forEach(engine -> assertFalse(engine.isOn()));

        // s0102
        airportFireTruck.getCabin().getSeats().forEach(seat -> assertFalse(seat.isOccupied()));

        // s0103
        driver.pressDoorButton(REAR);
        operator.pressDoorButton(REAR);
        airportFireTruck.getCabin().getDoors().forEach(door -> assertTrue(door.isOpen()));

        // s0104 - s0105
        testThrowerOffPosition();

        // s0106 - s0110
        LightConfiguration lightConfiguration = new LightConfiguration();
        lightConfiguration.roofLights = false;
        lightConfiguration.sideLights = false;
        lightConfiguration.frontLights = false;
        lightConfiguration.warningLights = false;
        lightConfiguration.blueLight = false;
        testLights(lightConfiguration);

        // s0111 - s0112
        testFullTanks();

        // s0113
        BatteryManagement.INSTANCE.charge(400000);
        assertEquals(400000, BatteryManagement.INSTANCE.getRemainingBatteryLevel());

        // s0114 - s0115
        testInitialThrowerKnobLevels();
    }

    @Test
    @Order(4)
    void handleInspectionDrive() {
        // s0201 - s0203
        testCommon();

        // s0204 - s0205
        testThrowerOffPosition();

        // s0206 - s0210
        LightConfiguration lightConfiguration = new LightConfiguration();
        lightConfiguration.roofLights = false;
        lightConfiguration.sideLights = false;
        lightConfiguration.blueLight = false;

        testLights(lightConfiguration);

        // s0211 - s0212
        testFullTanks();

        // s0213 - s0214
        testInitialThrowerKnobLevels();

        // s0215
        for (int i = 0; i < 7; i++) {
            driver.pressPedal(PedalType.GAS);
        }

        iterate(5);

        driver.turnSteeringWheel(LEFT, 5);
        iterate(3);
        assertEquals(-5, airportFireTruck.getCabin().getSteeringWheel().getPosition());
        airportFireTruck.getFrontAxles().forEach(frontAxle -> assertEquals(-5, frontAxle.getSteeringAngle()));

        // s0216
        driver.turnSteeringWheel(RIGHT, 5);
        iterate(5);
        assertEquals(0, airportFireTruck.getCabin().getSteeringWheel().getPosition());
        airportFireTruck.getFrontAxles().forEach(frontAxle -> assertEquals(0, frontAxle.getSteeringAngle()));

        // s0217
        driver.turnSteeringWheel(RIGHT, 5);
        iterate(5);
        assertEquals(5, airportFireTruck.getCabin().getSteeringWheel().getPosition());
        airportFireTruck.getFrontAxles().forEach(frontAxle -> assertEquals(5, frontAxle.getSteeringAngle()));

        // s0218
        for (int i = 0; i < 7; i++) {
            driver.pressPedal(PedalType.BRAKE);
        }

        // Rechnung:
        // Bremsweg: (1+..+6)*4 = 84
        // Beschleunigung: (1+...+7)*4 = 112
        // Konstante Fahrt: (5+5+3+5)*28 = 18*28 = 504 Iterationseinheiten
        // 504+112+84 = 700 Iterationseinheiten à 25 Zellen Verbrauch: 17500 ist der Gesamtverbrauch

        assertEquals(400000 - 17500, BatteryManagement.INSTANCE.getRemainingBatteryLevel());
    }

    @Test
    @Order(5)
    void handleEmergencyDrive() {
        // s0301 - s0303
        testCommon();

        // s0304 - s0305
        testThrowerOffPosition();

        // s0306 - s0310
        LightConfiguration lightConfiguration = new LightConfiguration();
        lightConfiguration.sideLights = false;
        testLights(lightConfiguration);

        // s0311 - s0312
        testFullTanks();

        // s0313 - s0314
        testInitialThrowerKnobLevels();

        // s0315
        for (int i = 0; i < 20; i++) {
            driver.pressPedal(PedalType.GAS);
        }
        iterate(10);

        // Rechnung:
        // Beschleunigung: (1+...+20)*4=840 Einheiten
        // Konstante Fahrt: 10*80=800 Einheiten
        // gesamt 1640 Einheiten * 25 Zellen Verbrauch: 41000 gesamt
        assertEquals(400000 - 41000, BatteryManagement.INSTANCE.getRemainingBatteryLevel());
    }

    @Test
    @Order(6)
    void handleFuelTruckOnFire() {
        // s0401 - s0403
        testCommon();

        // s0404 - s0408
        LightConfiguration lightConfiguration = new LightConfiguration();
        testLights(lightConfiguration);

        // s0409 - s0410
        testFullTanks();

        // s0411
        operator.useSwitch(RelatedDevice.SELF_PROTECTION);
        airportFireTruck.getFloorSprayNozzles().forEach(FloorSprayNozzle::spray);

        assertEquals(101250 - 700, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).findFirst().orElseThrow().getRemainingCapacity());

        // s0412 + s0413
        driver.pressPushButton(LEFT);
        for (int i = 0; i < 2; i++) {
            driver.pressPushButton(RIGHT);
        }
        for (int i = 0; i < 6; i++) {
            driver.turnThrower(Direction.RIGHT);
        }
        for (int i = 0; i < 3; i++) {
            driver.pressJoystickButton();
        }

        testThrowerConfiguration(ThrowerType.FRONT, 0, 3000, 5, 101250 - 700 - 8550, 33750 - 450);

        // s0414 + s0415
        operator.pressPushButton(LEFT);
        operator.pressPushButton(RIGHT);
        for (int i = 0; i < 2; i++) {
            operator.turnThrower(Direction.RIGHT);
        }
        for (int i = 0; i < 3; i++) {
            operator.pressJoystickButton();
        }

        testThrowerConfiguration(ThrowerType.ROOF, 16, C, 3, 101250 - 700 - 8550 - 7275, 33750 - 450 - 225);
    }

    @Test
    @Order(7)
    void handlePushbackVehicleOnFire() {
        // s0501 - s0503
        testCommon();

        // s0503 - s0508
        LightConfiguration lightConfiguration = new LightConfiguration();
        testLights(lightConfiguration);

        // s0509 - s0510
        testFullTanks();

        // s0511 + s0512
        driver.pressPushButton(LEFT);
        for (int i = 0; i < 3; i++) {
            driver.pressPushButton(RIGHT);
        }
        for (int i = 0; i < 7; i++) {
            driver.turnThrower(Direction.RIGHT);
        }
        for (int i = 0; i < 3; i++) {
            driver.pressJoystickButton();
        }

        testThrowerConfiguration(ThrowerType.FRONT, 0, 3500, 10, 101250 - 9450, 33750 - 1050);

        // s0513 + s0514
        operator.pressPushButton(LEFT);
        for (int i = 0; i < 2; i++) {
            operator.pressPushButton(RIGHT);
            operator.turnThrower(Direction.RIGHT);
        }
        for (int i = 0; i < 5; i++) {
            operator.pressJoystickButton();
        }

        testThrowerConfiguration(ThrowerType.ROOF, 16, C, 5, 101250 - 9450 - 11875, 33750 - 1050 - 625);

        for (int i = 0; i < 2; i++) {
            driver.pressPushButton(RIGHT);
        }
        for (int i = 0; i < 5; i++) {
            driver.turnThrower(Direction.LEFT);
        }
        for (int i = 0; i < 3; i++) {
            driver.pressJoystickButton();
        }

        testThrowerConfiguration(ThrowerType.FRONT, 0, 1000, 3, 101250 - 9450 - 11875 - 2910, 33750 - 1050 - 625 - 90);

        assertTrue(airportFireTruck.getFrontThrower().isActive());
    }

    @Test
    @Order(8)
    void handleAirplaneEngineFire() {
        // s0601 - s0603
        testCommon();

        // s0603 - s0608
        LightConfiguration lightConfiguration = new LightConfiguration();
        testLights(lightConfiguration);

        // s0609 - s0610
        testFullTanks();

        // s0611 + s0612
        driver.pressPushButton(LEFT);
        for (int i = 0; i < 3; i++) {
            driver.pressPushButton(RIGHT);
        }
        for (int i = 0; i < 7; i++) {
            driver.turnThrower(Direction.RIGHT);
        }
        for (int i = 0; i < 5; i++) {
            driver.pressJoystickButton();
        }
        testThrowerConfiguration(ThrowerType.FRONT, 0, 3500, 10, 101250 - 15750, 33750 - 1750);

        // s0613 + s0614
        operator.pressPushButton(LEFT);
        for (int i = 0; i < 3; i++) {
            operator.pressPushButton(RIGHT);
        }
        for (int i = 0; i < 3; i++) {
            operator.turnThrower(Direction.RIGHT);
        }
        for (int i = 0; i < 5; i++) {
            operator.pressJoystickButton();
        }

        testThrowerConfiguration(ThrowerType.ROOF, 16, C, 10, 101250 - 15750 - 11250, 33750 - 1750 - 1250);

        for (int i = 0; i < 5; i++) {
            operator.pressJoystickButton();
        }
        assertEquals(101250 - 15750 - 11250 - 11250, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).findFirst().orElseThrow().getRemainingCapacity());
        assertEquals(33750 - 1750 - 1250 - 1250, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.FOAM).findFirst().orElseThrow().getRemainingCapacity());

        for (int i = 0; i < 2; i++) {
            driver.pressPushButton(RIGHT);
        }
        for (int i = 0; i < 5; i++) {
            driver.turnThrower(Direction.LEFT);
        }
        for (int i = 0; i < 5; i++) {
            driver.pressJoystickButton();
        }
        assertEquals(101250 - 15750 - 11250 - 11250 - 4850, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).findFirst().orElseThrow().getRemainingCapacity());
        assertEquals(33750 - 1750 - 1250 - 1250 - 150, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.FOAM).findFirst().orElseThrow().getRemainingCapacity());
    }

    private void generateHelper() {
        driver = new Driver(airportFireTruck.getCabin().getPedals(),
                airportFireTruck.getCabin().getSteeringWheel(),
                airportFireTruck.getCabin().getControlPanel(),
                (FrontThrowerJoystick) airportFireTruck.getCabin().getFrontThrowerJoystick(),
                airportFireTruck.getCabin().getDoors().get(0).getButtons(),

                (FrontSeat) airportFireTruck.getCabin().getSeats().stream().filter(seat -> {
                    FrontSeat frontSeat = (FrontSeat) seat;
                    return frontSeat.getLeftRightSide().equals(LEFT);
                }).findFirst().orElseThrow()
        );

        operator = new Operator(airportFireTruck.getCabin().getControlPanel(),
                (RoofThrowerJoystick) airportFireTruck.getCabin().getRoofThrowerJoystick(),
                airportFireTruck.getCabin().getDoors().get(1).getButtons(),
                (FrontSeat) airportFireTruck.getCabin().getSeats().stream().filter(seat -> {
                    FrontSeat frontSeat = (FrontSeat) seat;
                    return frontSeat.getLeftRightSide().equals(RIGHT);
                }).findFirst().orElseThrow()
        );
    }

    private void testCommon() {
        generateHelper();
        BatteryManagement.INSTANCE.charge(400000);

        // s0X01
        driver.pressDoorButton(REAR);
        driver.sitOrGetUp();
        operator.pressDoorButton(REAR);
        operator.sitOrGetUp();

        airportFireTruck.getCabin().getSeats().stream().filter(seat -> seat instanceof FrontSeat).forEach(seat -> assertTrue(seat.isOccupied()));

        // s0X02
        driver.pressDoorButton(REAR);
        operator.pressDoorButton(REAR);
        airportFireTruck.getCabin().getDoors().forEach(door -> assertFalse(door.isOpen()));

        // s0X03
        operator.useSwitch(RelatedDevice.ENGINES);
        airportFireTruck.getEngines().forEach(engine -> assertTrue(engine.isOn()));
    }

    private void testLights(LightConfiguration lightConfiguration) {
        // In the beginning all lights are off.
        // If null is detected. Don't check the light because it's not specified in the scenario.

        if (lightConfiguration.roofLights) {
            operator.useSwitch(RelatedDevice.ROOF_LIGHTS);
            airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertTrue(headLight.isOn()));
        } else {
            airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertFalse(headLight.isOn()));
        }

        if (lightConfiguration.roofLights) {
            operator.useSwitch(RelatedDevice.SIDE_LIGHTS);
            airportFireTruck.getSideLights().forEach(sideLight -> assertTrue(sideLight.isOn()));
        } else {
            airportFireTruck.getSideLights().forEach(sideLight -> assertFalse(sideLight.isOn()));
        }

        if (lightConfiguration.frontLights) {
            operator.useSwitch(RelatedDevice.FRONT_LIGHTS);
            airportFireTruck.getHeadLights().stream().filter(roofLight -> roofLight.getPosition().equals(BOTTOM)).forEach(roofLight -> assertTrue(roofLight.isOn()));
        } else {
            airportFireTruck.getHeadLights().stream().filter(roofLight -> roofLight.getPosition().equals(BOTTOM)).forEach(roofLight -> assertFalse(roofLight.isOn()));
        }

        if (lightConfiguration.warningLights) {
            operator.useSwitch(RelatedDevice.WARNING_LIGHTS);
            airportFireTruck.getWarningLights().forEach(warningLight -> assertTrue(warningLight.isOn()));
        } else {
            airportFireTruck.getWarningLights().forEach(warningLight -> assertFalse(warningLight.isOn()));
        }

        if (lightConfiguration.blueLight) {
            operator.useSwitch(RelatedDevice.BLUE_LIGHTS);
            airportFireTruck.getBlueLights().forEach(blueLight -> assertTrue(blueLight.isOn()));
        } else {
            airportFireTruck.getBlueLights().forEach(blueLight -> assertFalse(blueLight.isOn()));
        }

    }

    private void testThrowerOffPosition() {
        // RoofThrower down
        assertEquals(0, airportFireTruck.getRoofThrower().getLowerSegment().getDegree());
        assertEquals(0, airportFireTruck.getRoofThrower().getUpperSegment().getLength());

        // FrontThrower is deactive
        assertFalse(airportFireTruck.getFrontThrower().isActive());
    }

    private void testFullTanks() {
        List<Tank> tanks = airportFireTruck.getRoofThrower().getMixer().getTanks();

        Tank testedTank = tanks.stream().filter(tank -> tank.getType().equals(ExtinguishingAgent.WATER)).findFirst().orElseThrow();
        testedTank.fill(101250);
        assertEquals(101250, testedTank.getRemainingCapacity());

        testedTank = tanks.stream().filter(tank -> tank.getType().equals(ExtinguishingAgent.FOAM)).findFirst().orElseThrow();
        testedTank.fill(33750);
        assertEquals(33750, testedTank.getRemainingCapacity());
    }

    private void testInitialThrowerKnobLevels() {
        List<IRotaryKnob> rotaryKnobs = airportFireTruck.getCabin().getControlPanel().getKnobs();

        FrontThrowerKnob frontThrowerKnob = (FrontThrowerKnob) rotaryKnobs.stream().filter(knob -> knob.getType().equals(ThrowerType.FRONT)).findFirst().orElseThrow();
        assertEquals(0, frontThrowerKnob.getLevel());

        RoofThrowerKnob roofThrowerKnob = (RoofThrowerKnob) rotaryKnobs.stream().filter(knob -> knob.getType().equals(ThrowerType.ROOF)).findFirst().orElseThrow();
        assertEquals(RoofThrowerLevel.A, roofThrowerKnob.getLevel());
    }

    private void iterate(int numberOfIterations) {
        for (int i = 0; i < numberOfIterations; i++) {
            for (IEngine engine : airportFireTruck.getEngines()) {
                engine.rotate();
            }
        }
    }

    private void testThrowerConfiguration(ThrowerType type, int length, Object level, int mixingRatio, int expectedRemainingWater, int expectedRemainingFoam) {

        if (type.equals(ThrowerType.FRONT)) {
            assertTrue(airportFireTruck.getFrontThrower().isActive());
            assertEquals(90, airportFireTruck.getFrontThrower().getDegree());
            assertEquals(level, airportFireTruck.getFrontThrower().getLevel());
            assertEquals(mixingRatio, airportFireTruck.getFrontThrower().getMixingRatio());
            assertEquals(expectedRemainingWater, airportFireTruck.getFrontThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).findFirst().orElseThrow().getRemainingCapacity());
            assertEquals(expectedRemainingFoam, airportFireTruck.getFrontThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.FOAM).findFirst().orElseThrow().getRemainingCapacity());

        } else {
            assertTrue(airportFireTruck.getRoofThrower().isActive());
            assertEquals(90, airportFireTruck.getRoofThrower().getLowerSegment().getDegree());
            assertEquals(length, airportFireTruck.getRoofThrower().getUpperSegment().getLength());
            assertEquals(level, airportFireTruck.getRoofThrower().getLevel());
            assertEquals(mixingRatio, airportFireTruck.getRoofThrower().getMixingRatio());
            assertEquals(expectedRemainingWater, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER).findFirst().orElseThrow().getRemainingCapacity());
            assertEquals(expectedRemainingFoam, airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.FOAM).findFirst().orElseThrow().getRemainingCapacity());
        }
    }

}
