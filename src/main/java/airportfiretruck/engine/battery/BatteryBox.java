package airportfiretruck.engine.battery;

public class BatteryBox {
    private final Battery[][] battery = new Battery[2][2];

    public BatteryBox() {
        battery[0][0] = new Battery();
        battery[0][1] = new Battery();
        battery[1][0] = new Battery();
        battery[1][1] = new Battery();
    }

    public void charge(int amount) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int free = (int) battery[i][j].getRemainingBatteryLevel();
                switch (free) {
                    case 100000:
                        continue;
                    case 0:
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
        int taken = 0;
        for (int i = 0; i < 2 && amount != 0; i++) {
            for (int j = 0; j < 2 && amount != 0; j++) {
                int temp = battery[i][j].takeOut(amount);
                amount -= temp;
                taken += temp;
            }
        }
        return taken;
    }

    public double getRemainingBatteryLevel() {
        double tlevel = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                tlevel += battery[i][j].getRemainingBatteryLevel();
            }
        }
        return tlevel;
    }
}
