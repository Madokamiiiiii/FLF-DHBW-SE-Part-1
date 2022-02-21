package airportfiretruck.extinguisher.watersupply;

import java.util.List;

public class Mixer {

    private final List<Tank> tanks;

    public Mixer(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public int getLiquid(int amount, int foamRatio) {
        int foam = 0;
        int water = 0;
        for (Tank tank : tanks) {
            if (tank.getType() == ExtinguishingAgent.FOAM) {
                foam = tank.getLiquid(amount * foamRatio / 100);
            } else {
                water = tank.getLiquid(amount * (100 - foamRatio) / 100);
            }
        }

        // This simulates the mixing process
        return foam + water;
    }

    public List<Tank> getTanks() {
        return tanks;
    }
}
