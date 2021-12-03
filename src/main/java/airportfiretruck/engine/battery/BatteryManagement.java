package airportfiretruck.engine.battery;

public enum BatteryManagement {
    INSTANCE;

    private BatteryBox batteryBox;

    public void charge(int amount) {
        batteryBox.charge(amount);
    }

    public int takeOut(int amount) {

        return batteryBox.takeOut(amount);
    }

    public double getRemainingBatteryLevel() {
        return batteryBox.getRemainingBatteryLevel();
    }
}
