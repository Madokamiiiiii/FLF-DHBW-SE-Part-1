package airportfiretruck.battery;

public enum BatteryManagement {
    INSTANCE;

    private BatteryBox bbox;

    public void charge(int amount) {
        bbox.charge(amount);
    }

    public int takeOut(int amount) {

        return bbox.takeOut(amount);
    }

    public double getRemainingBatteryLevel() {
        return bbox.getRemainingBatteryLevel();
    }
}
