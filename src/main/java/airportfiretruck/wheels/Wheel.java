package airportfiretruck.wheels;

import java.util.List;

public class Wheel {
    private Tire tire;
    private List<BrakeDisk> brakeDisks;

    public List<BrakeDisk> getBrakeDisks() {
        return brakeDisks;
    }

    public void setBrakeDisks(List<BrakeDisk> brakeDisks) {
        this.brakeDisks = brakeDisks;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }
}
