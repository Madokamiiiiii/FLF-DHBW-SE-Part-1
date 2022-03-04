import java.util.ArrayList;
import java.util.List;

public class Mixer {
    private static final Mixer instance = new Mixer();
    public Port port;

    private List<ITank> tanks = new ArrayList<>();

    private Mixer() {
        port = new Port();
    }

    public int innerGetMix(int amount, int foamRatio) {
        int foam = 0;
        int water = 0;
        for (ITank tank : tanks) {
            if (tank.getType() == ExtinguishingAgent.FOAM) {
                foam = tank.getLiquid(amount * foamRatio / 100);
            } else {
                water = tank.getLiquid(amount * (100 - foamRatio) / 100);
            }
        }

        // This simulates the mixing process
        return foam + water;
    }

    public List<ITank> innerGetTanks() {
        return tanks;
    }

    public void innerSetTanks(List<ITank> tanks) {
        this.tanks = tanks;
    }

    public static Mixer getInstance() {
        return instance;
    }

    class Port implements IMixer {

        @Override
        public int getMix(Integer amount, Integer foamRatio) {
            return innerGetMix(amount, foamRatio);
        }

        @Override
        public List<ITank> getTanks() {
            return innerGetTanks();
        }

        @Override
        public void setTanks(List<ITank> tanks) {
            innerSetTanks(tanks);
        }
    }
}
