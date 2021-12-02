import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.Cabin;
import airportfiretruck.cabin.SteeringWheel;
import airportfiretruck.cabin.joysticks.FrontThrowerJoystick;
import airportfiretruck.cabin.joysticks.RoofThrowerJoystick;
import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.cabin.pedals.Pedal;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.cabin.seats.FrontSeat;
import airportfiretruck.cabin.seats.Seat;
import airportfiretruck.lights.*;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.Color;
import airportfiretruck.lights.led.LightSize;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static airportfiretruck.position.FrontRearSide.FRONT;
import static airportfiretruck.position.FrontRearSide.REAR;
import static airportfiretruck.position.LeftRightSide.*;
import static airportfiretruck.position.Position.BOTTOM;
import static airportfiretruck.position.Position.TOP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(4, testLightPosition(headLight, TOP, MIDDLE, FRONT));
        assertEquals(3, testLightPosition(headLight, BOTTOM, RIGHT, FRONT));
        assertEquals(3, testLightPosition(headLight, BOTTOM, LEFT, FRONT));

        // Test Direction Indicator
        List<DirectionIndicatorLight> directionIndicatorLights = airportFireTruck.getDirectionIndicatorLights();
        assertEquals(4, directionIndicatorLights.size());
        assertEquals(1, testLightPosition(directionIndicatorLights, null, RIGHT, FRONT));
        assertEquals(1, testLightPosition(directionIndicatorLights, null, LEFT, FRONT));
        assertEquals(1, testLightPosition(directionIndicatorLights, null, RIGHT, REAR));
        assertEquals(1, testLightPosition(directionIndicatorLights, null, LEFT, REAR));

        // Test Brake Lights
        List<BrakeLight> brakeLights = airportFireTruck.getBrakeLights();
        assertEquals(2, brakeLights.size());
        assertEquals(1, testLightPosition(brakeLights, null, RIGHT, REAR));
        assertEquals(1, testLightPosition(brakeLights, null, LEFT, REAR));

        // Test SideLights
        List<SideLight> sideLights = airportFireTruck.getSideLights();
        assertEquals(10, sideLights.size());
        assertEquals(5, testLightPosition(sideLights, null, RIGHT, null));
        assertEquals(5, testLightPosition(sideLights, null, LEFT, null));

        // Test BlueLights
        List<BlueLight> blueLights = airportFireTruck.getBlueLights();
        assertEquals(10, blueLights.size());
        assertEquals(1, testLedLightAndPosition(blueLights, BOTTOM, LEFT, FRONT, LightSize.SMALL));
        assertEquals(1, testLedLightAndPosition(blueLights, BOTTOM, RIGHT, FRONT, LightSize.SMALL));
        assertEquals(2, testLedLightAndPosition(blueLights, TOP, LEFT, FRONT, LightSize.LARGE));
        assertEquals(2, testLedLightAndPosition(blueLights, TOP, RIGHT, FRONT, LightSize.LARGE));
        assertEquals(2, testLedLightAndPosition(blueLights, BOTTOM, LEFT, REAR, LightSize.MEDIUM));
        assertEquals(2, testLedLightAndPosition(blueLights, BOTTOM, RIGHT, REAR, LightSize.MEDIUM));

        // Test WarningLights
        List<WarningLight> warningLights = airportFireTruck.getWarningLights();
        assertEquals(2, warningLights.size());
        assertEquals(1, testLedLightAndPosition(warningLights, TOP, LEFT, FRONT));
        assertEquals(1, testLedLightAndPosition(warningLights, TOP, RIGHT, REAR));

        // Test Cabin
        Cabin cabin = airportFireTruck.getCabin();

        assertEquals(2, cabin.getDoors().size());
        cabin.getDoors().forEach(busDoor -> assertEquals(2, busDoor.getButtons().size()));

        List<Seat> seats = cabin.getSeats();
        assertEquals(4, seats.size());
        assertEquals(2, seats.stream().filter(seat -> seat instanceof FrontSeat).count());
        seats.forEach(seat -> assertNotNull(seat.getRespirator()));

        FrontThrowerJoystick frontThrowerJoystick = cabin.getFrontThrowerJoystick();
        assertNotNull(frontThrowerJoystick);
        assertNotNull(frontThrowerJoystick.getJoystickButton());
        assertEquals(2, frontThrowerJoystick.getPushButtons().size());

        RoofThrowerJoystick roofThrowerJoystick = cabin.getRoofThrowerJoystick();
        assertNotNull(roofThrowerJoystick);
        assertNotNull(roofThrowerJoystick.getJoystickButton());
        assertEquals(2, roofThrowerJoystick.getPushButtons().size());

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

        // Test thrower
    }

    private long testLedLightAndPosition(List<BlueLight> lights, Position position, LeftRightSide leftRightSide, FrontRearSide frontRearSide, LightSize size) {
        lights = lights.stream().filter(light -> light.getLightSize().equals(size)
                && light.getColor().equals(Color.BLUE)).collect(Collectors.toList());
        return testLightPosition(lights, position, leftRightSide, frontRearSide);
    }

    private long testLedLightAndPosition(List<WarningLight> lights, Position position, LeftRightSide leftRightSide, FrontRearSide frontRearSide) {
        lights = lights.stream().filter(light -> light.getColor().equals(Color.ORANGE)).collect(Collectors.toList());
        return testLightPosition(lights, position, leftRightSide, frontRearSide);
    }

    private long testLightPosition(List<? extends Light> lights, Position position, LeftRightSide leftRightSide, FrontRearSide frontRearSide) {
        return lights.stream().filter(light -> {
            if (Objects.nonNull(position)) {
                return light.getPosition().equals(position);
            }
            return true;
        }).filter(light -> {
            if (Objects.nonNull(leftRightSide)) {
                return light.getLeftRightSide().equals(leftRightSide);
            }
            return true;
        }).filter(light -> {
            if (Objects.nonNull(frontRearSide)) {
                return light.getFrontRearSide().equals(frontRearSide);
            }
            return true;
        }).count();
    }

}
