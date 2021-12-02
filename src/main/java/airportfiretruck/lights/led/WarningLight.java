package airportfiretruck.lights.led;

import airportfiretruck.lights.Light;

import java.util.List;

public class WarningLight extends Light {
    final Color color = Color.ORANGE;
    private Led led;
    // composition mit Led
    public WarningLight() {
        this.led=new Led(Color.ORANGE);
    }
}
