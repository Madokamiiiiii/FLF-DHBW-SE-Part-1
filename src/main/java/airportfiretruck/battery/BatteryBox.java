package airportfiretruck.battery;

public class BatteryBox {
    private Battery[][] battery;

    public BatteryBox() {
        battery = new Battery[2][2];
    }
    public void charge(int amount) {

    }
    public int takeOut(int amount) {
        return 0;
    }
    public double getRemainingBatteryLevel() {
        return 0.0;
    }
}
