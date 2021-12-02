package airportfiretruck.lights.led;

import airportfiretruck.lights.Light;

public class WarningLight extends Light {
    final Color color = Color.ORANGE;
    private final Led led;

    // composition mit Led
    public WarningLight() {
        this.led = new Led(Color.ORANGE);
    }

    public Color getColor() {
        return color;
    }

    public Led getLed() {
        return led;
    }
}
