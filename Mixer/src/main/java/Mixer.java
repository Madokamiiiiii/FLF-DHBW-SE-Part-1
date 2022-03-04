import java.util.ArrayList;
import java.util.List;

public class Mixer {
    private static final Mixer instance = new Mixer();
    public Port port;

    public final List<ITank> tanks = new ArrayList<>();

    private Mixer() {
        port = new Port();

        tanks.add(new Tank(ExtinguishingAgent.WATER));
        tanks.add(new Tank(ExtinguishingAgent.FOAM));

        System.out.println(tanks);
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

    public static Mixer getInstance() {
        return instance;
    }

    public class Port implements IMixer {

        @Override
        public int getMix(Integer amount, Integer foamRatio) {
            return innerGetMix(amount, foamRatio);
        }

        @Override
        public List<ITank> getTanks() {
            return innerGetTanks();
        }
    }
}
