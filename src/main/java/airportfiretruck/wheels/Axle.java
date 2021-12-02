package airportfiretruck.wheels;

import java.util.ArrayList;
import java.util.List;

public class Axle {
    private List<Wheel> wheels;

    public Axle() {
        wheels = new ArrayList<>();
        wheels.add(0,new Wheel());
        wheels.add(1,new Wheel());
    }
}
