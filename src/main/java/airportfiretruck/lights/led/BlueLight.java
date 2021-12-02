package airportfiretruck.lights.led;

import airportfiretruck.lights.Light;

import java.util.ArrayList;
import java.util.List;

public class BlueLight extends Light {
    private LightSize lightSize;
    private List<Led> leds;

    public void setLightSize(LightSize lightSize) {
        this.lightSize = lightSize;
    }

    final Color color = Color.BLUE;
    // composition mit led
    public BlueLight(LightSize lightSize) {
        this.leds = new ArrayList<>();
        switch (lightSize) {
            case SMALL -> {
                this.lightSize = lightSize;
                leds.add(0,new Led(Color.BLUE));
            }
            case MEDIUM -> {
                this.lightSize = lightSize;
                leds.add(0,new Led(Color.BLUE));
                leds.add(1,new Led(Color.BLUE));
            }
            case LARGE -> {
                this.lightSize = lightSize;
                for (int i = 0; i < 4; i++) {
                    leds.add(i,new Led(Color.BLUE));
                }
            }
        }
    }
}
