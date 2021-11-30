package airportfiretruck.battery;

public class Battery {
    private BatteryStatus status;
    private int[][][] capacity;

    public Battery() {
        capacity = new int[100][10][100];
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
