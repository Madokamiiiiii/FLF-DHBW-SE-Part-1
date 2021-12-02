package airportfiretruck.extinguisher.watersupply;

public class Tank {
    private int[][][] capacity;
    private final ExtinguishingAgent type;
    private int[] pointer;

    public Tank(ExtinguishingAgent type) {
        this.type = type;
        switch (type) {
            case WATER -> {
                capacity = new int[75][30][15];
                pointer = new int[]{0, 0, 14};
                fill(75 * 30 * 15);
            }
            case FOAM -> {
                capacity = new int[75][30][5];
                pointer = new int[]{0, 0, 4};
                fill(75 * 30 * 5);
            }
        }
    }

    public void fill(int amount) {
        //oben links : 0,0,0 -> unten rechts : max,max,max
        for (; amount > 0; amount--) {
            if (pointer[1] < capacity[0].length) {
                if (pointer[0] < capacity.length) {
                    if (pointer[2] > 0) {
                        capacity[pointer[0]][pointer[1]][pointer[2]] = 1;
                        pointer[2]--;
                        continue;
                    }
                    if (pointer[0] < capacity.length - 1) {
                        pointer[2] = capacity[0][0].length - 1;
                        pointer[0]++;
                        capacity[pointer[0]][pointer[1]][pointer[2]] = 1;
                        pointer[2]--;
                        continue;
                    }
                }
                if (pointer[1] < capacity[0].length - 1) {
                    pointer[2] = capacity[0][0].length - 1;
                    pointer[0] = 0;
                    pointer[1]++;
                    capacity[pointer[0]][pointer[1]][pointer[2]] = 1;
                    pointer[2]--;
                    continue;
                }
            }
            return;
        }
    }

    public int getLiquid(int amount) {
        //oben links : 0,0,0 -> unten rechts : max,max,max
        int pumped = 0;
        for (; amount > 0; amount--) {
            if (pointer[1] > 0) {
                if (pointer[0] > 0) {
                    if (pointer[2] < capacity[0][0].length - 1) {
                        capacity[pointer[0]][pointer[1]][pointer[2]] = 0;
                        pointer[2]++;
                        pumped++;
                        continue;
                    }
                    pointer[2] = 0;
                    pointer[0]--;
                    capacity[pointer[0]][pointer[1]][pointer[2]] = 0;
                    pointer[2]++;
                    pumped++;
                    continue;
                }
                pointer[2] = 0;
                pointer[0] = capacity.length - 1;
                pointer[1]--;
                capacity[pointer[0]][pointer[1]][pointer[2]] = 0;
                pointer[2]++;
                pumped++;
                continue;
            }
            return pumped;
        }
        return pumped;
    }

    public ExtinguishingAgent getType() {
        return type;
    }
}
