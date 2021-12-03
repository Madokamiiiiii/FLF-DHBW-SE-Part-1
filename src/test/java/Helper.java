import airportfiretruck.lights.Light;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.Color;
import airportfiretruck.lights.led.LightSize;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.position.FrontRearSide;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Helper {

    public static long testLedLightAndPosition(List<BlueLight> lights, Position position, LeftRightSide leftRightSide, FrontRearSide frontRearSide, LightSize size) {
        lights = lights.stream().filter(light -> light.getLightSize().equals(size)
                && light.getColor().equals(Color.BLUE)).collect(Collectors.toList());
        return testLightPosition(lights, position, leftRightSide, frontRearSide);
    }

    public static long testLedLightAndPosition(List<WarningLight> lights, Position position, LeftRightSide leftRightSide, FrontRearSide frontRearSide) {
        lights = lights.stream().filter(light -> light.getColor().equals(Color.ORANGE)).collect(Collectors.toList());
        return testLightPosition(lights, position, leftRightSide, frontRearSide);
    }

    public static long testLightPosition(List<? extends Light> lights, Position position, LeftRightSide leftRightSide, FrontRearSide frontRearSide) {
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
