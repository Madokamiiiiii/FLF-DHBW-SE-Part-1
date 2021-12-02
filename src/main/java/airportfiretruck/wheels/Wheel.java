package airportfiretruck.wheels;

import java.util.List;

public class Wheel {
    private Tire tire;
    private List<BrakeDisk> brakeDisks;

    public Wheel() {
        tire = new Tire();
        brakeDisks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            brakeDisks.add(i,new BrakeDisk());
        }
    }
}
