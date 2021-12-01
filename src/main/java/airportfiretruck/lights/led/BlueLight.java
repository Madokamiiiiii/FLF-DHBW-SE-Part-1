package airportfiretruck.lights.led;

import airportfiretruck.lights.Light;

public class BlueLight extends Light {
    private LightSize lightSize;

    public void setLightSize(LightSize lightSize) {
        this.lightSize = lightSize;
    }

    final Color color = Color.BLUE;
    // composition mit led
}
