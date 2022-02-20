package airportfiretruck.engine.battery;

public class Battery {
    private int pointer;
    private final int[][][] capacity;

    public Battery() {
        capacity = new int[100][10][100];
        pointer = 0;
    }

    public void charge(int amount) {
        int[] index = pointerAsIndex();
        for (; amount > 0; amount--) {
            // Battery charged
            if (pointer > 99999) {
                return;
            }
            if (index[2] < 100) {
                capacity[index[0]][index[1]][index[2]] = 1;
                pointer++;
                index[2]++;
                continue;
            }
            index[0]++;
            // Reihe ist voll (LxHx100)
            if (index[0] < 100) {
                index[2] = 0;
                capacity[index[0]][index[1]][index[2]] = 1;
                pointer++;
                index[2]++;
                continue;
            }
            index[1]++;
            // Fläche ist voll (100xHx100)
            if (index[1] < 10) {
                index[2] = 0;
                index[0] = 0;
                capacity[index[0]][index[1]][index[2]] = 1;
                pointer++;
                index[2]++;
                continue;
            }
            return;
        }
        // Fertig geladen oder Batterie voll
    }

    public int takeOut(int amount) {
        int taken = 0;
        pointer--;
        int[] index = pointerAsIndex();
        pointer++;
        for (; amount > 0; amount--) {
            // Battery charged
            if (pointer < 1) {
                return taken;
            }
            if (index[2] > -1) {
                capacity[index[0]][index[1]][index[2]] = 0;
                taken++;
                pointer--;
                index[2]--;
                continue;
            }
            index[0]--;
            // Reihe ist voll (LxHx100)
            if (index[0] > -1) {
                index[2] = 99;
                capacity[index[0]][index[1]][index[2]] = 0;
                taken++;
                pointer--;
                index[2]--;
                continue;
            }
            index[1]--;
            // Fläche ist voll (100xHx100)
            if (index[1] > -1) {
                index[2] = 99;
                index[0] = 99;
                capacity[index[0]][index[1]][index[2]] = 0;
                taken++;
                pointer--;
                index[2]--;
            }
        }
        return taken;
    }

    private int[] pointerAsIndex() {
        int y = pointer / 10000;
        int x = (pointer % 10000) / 100;
        int z = pointer % 100;
        return new int[]{x, y, z};
    }

    public double getRemainingBatteryLevel() {
        return pointer;
    }
}
