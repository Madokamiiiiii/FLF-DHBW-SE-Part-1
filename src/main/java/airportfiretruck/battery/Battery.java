package airportfiretruck.battery;

public class Battery {
    private BatteryStatus status;
    private int[] pointer;
    private int[][][] capacity;

    public Battery() {
        capacity = new int[100][10][100];
        pointer = new int[]{99,9,99};
    }
    public void charge(int amount) {
        for (;amount > 0;amount--) {
            if ( pointer[1] < 10) {
                if (pointer[0] < 100) {
                    if (pointer[2] < 100) {
                        capacity[pointer[0]][pointer[1]][pointer[2]] = 1;
                        pointer[2]++;
                        continue;
                    }
                    pointer[2]=0;
                    pointer[0]++;
                    capacity[pointer[0]][pointer[1]][pointer[2]] = 1;
                    pointer[2]++;
                    continue;
                }
                pointer[2]=0;
                pointer[0]= 0;
                pointer[1]++;
                capacity[pointer[0]][pointer[1]][pointer[2]] = 1;
                pointer[2]++;
                continue;
            }
            return;
        }
    }
    public int takeOut(int amount) {
        int taken = 0;
        for (;amount > 0;amount--) {
            if ( pointer[1] > 0) {
                if (pointer[0] > 0) {
                    if (pointer[2] > 0) {
                        capacity[pointer[0]][pointer[1]][pointer[2]] = 0;
                        pointer[2]--;
                        taken++;
                        continue;
                    }
                    pointer[2]=99;
                    pointer[0]--;
                    capacity[pointer[0]][pointer[1]][pointer[2]] = 0;
                    pointer[2]--;
                    taken++;
                    continue;
                }
                pointer[2]=99;
                pointer[0]= 99;
                pointer[1]--;
                capacity[pointer[0]][pointer[1]][pointer[2]] = 0;
                pointer[2]--;
                taken++;
                continue;
            }
            return taken;
        }
        return taken;
    }
    public double getRemainingBatteryLevel() {
        int base = pointer[1]*10000;
        int toplayer = (pointer[0]+1)*(pointer[2]+1);
        return base+toplayer;
    }
}
