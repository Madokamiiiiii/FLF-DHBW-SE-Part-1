package airportfiretruck.cabin.displays;

public class BatteryDisplay implements IDisplay {

    private double remainingCharge;

    @Override
    public void setValue(double remainingCharge) {
        this.remainingCharge = Math.round(remainingCharge / 40.0) / 100.0;
    }

    @Override
    public String display() {
        return remainingCharge + "%";
    }
}
