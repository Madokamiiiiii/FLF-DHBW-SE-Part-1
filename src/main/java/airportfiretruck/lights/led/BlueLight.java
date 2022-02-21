package airportfiretruck.lights.led;

import airportfiretruck.lights.Light;

import java.util.ArrayList;
import java.util.List;

public class BlueLight extends Light {
    private final List<Led> leds;
    private LightSize lightSize;

    // composition mit led
    public BlueLight(LightSize lightSize) {
        this.leds = new ArrayList<>();
        switch (lightSize) {
            case SMALL -> {
                this.lightSize = lightSize;
                leds.add(0, new Led(Color.BLUE));
            }
            case MEDIUM -> {
                this.lightSize = lightSize;
                leds.add(0, new Led(Color.BLUE));
                leds.add(1, new Led(Color.BLUE));
            }
            case LARGE -> {
                this.lightSize = lightSize;
                for (int i = 0; i < 4; i++) {
                    leds.add(i, new Led(Color.BLUE));
                }
            }
        }
    }

    public LightSize getLightSize() {
        return lightSize;
    }

    public Color getColor() {
        return leds.get(0).getColor();
    }
}
