import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.Cabin;
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
