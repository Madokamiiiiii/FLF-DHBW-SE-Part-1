package airportfiretruck.wheels;

import java.util.List;

public class Wheel {
    private Tire tire;
    private List<BrakeDisk> brakeDisks;

    public Wheel() {
    }

    public void setBrakeDisks(List<BrakeDisk> brakeDisks) {
        this.brakeDisks = brakeDisks;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }
}
