package airportfiretruck.lights.led;

import airportfiretruck.lights.Light;

public class WarningLight extends Light {
    private final Led led;

    // composition mit Led
    public WarningLight() {
        this.led = new Led(Color.ORANGE);
    }

    public Color getColor() {
        return led.getColor();
    }

    public Led getLed() {
        return led;
    }
}
