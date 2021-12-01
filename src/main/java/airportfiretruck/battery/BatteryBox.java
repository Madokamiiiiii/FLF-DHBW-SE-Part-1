package airportfiretruck.battery;

public class BatteryBox {
    private final Battery[][] battery;

    public BatteryBox() {
        battery = new Battery[2][2];
    }

    public void charge(int amount) {
        for (int i = 2; i > 0; i--) {
            for (int j = 2; j > 0; j--) {
                int free = (int) battery[i][j].getRemainingBatteryLevel() - 100000;
                switch (free) {
                    case 0:
                        continue;
                    case 100000:
                        battery[i][j].charge(amount);
                        if (amount > 100000) {
                            amount -= 100000;
                        }
                        break;
                    default:
                        battery[i][j].charge(amount);
                        return;
                }
            }
        }
    }

    public int takeOut(int amount) {
        for (int i = 0; i < 2 && amount != 0; i++) {
            for (int j = 0; j < 2 && amount != 0; j++) {
                int temp = battery[i][j].takeOut(amount);
                amount -= temp;
            }
        }
        return amount;
    }

    public double getRemainingBatteryLevel() {
        double tlevel = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                tlevel += battery[i][j].getRemainingBatteryLevel();
            }
        }
        return tlevel / 4;
    }
}
