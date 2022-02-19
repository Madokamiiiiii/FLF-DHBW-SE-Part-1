package airportfiretruck.extinguisher.watersupply;

public class Tank {
    private int[][][] capacity;
    private final ExtinguishingAgent type;
    private int pointer = 0;

    public Tank(ExtinguishingAgent type) {
        this.type = type;
        switch (type) {
            case WATER -> capacity = new int[75][45][30];
            case FOAM -> capacity = new int[75][45][10];
        }
    }

    private int[] pointerAsIndex() {
        int y = pointer / (75 * capacity[0][0].length);
        int x = (pointer % (75 * capacity[0][0].length)) / capacity[0][0].length;
        int z = capacity[0][0].length - pointer % capacity[0][0].length - 1;
        return new int[]{x, y, z};
    }

    public void fill(int amount) {
        int pointerMax = 75 * 45 * capacity[0][0].length - 1;
        int[] index = pointerAsIndex();
        System.out.println(index[0] + "." + index[1] + "." + index[2]);
        for (; amount > 0; amount--) {
            // tank is full
            if (pointer > pointerMax) {
                return;
            }
            if (index[2] > -1) {
                capacity[index[0]][index[1]][index[2]] = 1;
                pointer++;
                index[2]--;
                continue;
            }
            index[0]++;
            // Reihe ist voll (LxHx100)
            if (index[0] < 75) {
                index[2] = capacity[0][0].length - 1;
                capacity[index[0]][index[1]][index[2]] = 1;
                pointer++;
                index[2]--;
                continue;
            }
            index[1]++;
            // Fläche ist voll (100xHx100)
            if (index[1] < 45) {
                index[2] = capacity[0][0].length - 1;
                index[0] = 0;
                capacity[index[0]][index[1]][index[2]] = 1;
                pointer++;
                index[2]--;
                continue;
            }
            return;
        }
        // Fertig/Tank voll
    }

    public int getLiquid(int amount) {
        int pumped = 0;
        pointer--;
        int[] index = pointerAsIndex();
        pointer++;
        for (; amount > 0; amount--) {
            // Tank ist leer
            if (pointer < 1) {
                return pumped;
            }
            if (index[2] < capacity[0][0].length) {
                capacity[index[0]][index[1]][index[2]] = 0;
                pumped++;
                pointer--;
                index[2]++;
                continue;
            }
            index[0]--;
            // Reihe ist leer (LxHx100)
            if (index[0] > -1) {
                index[2] = 0;
                capacity[index[0]][index[1]][index[2]] = 0;
                pumped++;
                pointer--;
                index[2]++;
                continue;
            }
            index[1]--;
            // Fläche ist leer (100xHx100)
            if (index[1] > -1) {
                index[2] = 0;
                index[0] = 74;
                capacity[index[0]][index[1]][index[2]] = 0;
                pumped++;
                pointer--;
                index[2]++;
            }
        }
        return pumped;
    }

    public int getRemainingCapacity() {
        int total = 0;
        for (int[][] area : capacity) {
            for (int[] row : area) {
                for (int cell : row) {
                    total += cell;
                }
            }
        }
        return total;
    }

    public ExtinguishingAgent getType() {
        return type;
    }
}
