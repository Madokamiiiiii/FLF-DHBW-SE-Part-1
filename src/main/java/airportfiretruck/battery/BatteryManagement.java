package airportfiretruck.battery;

public enum BatteryManagement {
    INSTANCE;

    private BatteryBox bbox;

    public void charge (int amount) {

    }
    public int takeOut (int amount) {
        return 0;
    }
    public double getRemainingBatteryLevel() {
        return 0.0;
    }
}
