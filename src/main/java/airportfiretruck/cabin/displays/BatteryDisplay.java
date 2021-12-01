package airportfiretruck.cabin.displays;

public class BatteryDisplay implements IDisplay {

    private double value;

    public BatteryDisplay() {

    }

    public void setValue(double value) {
        this.value = Math.round(value / 40.0) / 100.0;
    }

    public String display() {
        return value + "%";
    }
}
