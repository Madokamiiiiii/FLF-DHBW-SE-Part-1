package airportfiretruck.extinguisher.watersupply;

import airportfiretruck.extinguisher.thrower.IThrowerMixer;

import java.util.ArrayList;
import java.util.List;


// TODO: Mergen?
public class MixerAlt {

    private int foamRatio;
    private IThrowerMixer throwerMixer;
    private final List<Tank> tanks;

    public MixerAlt() {
        tanks = new ArrayList<>();
        tanks.add(0, new Tank(ExtinguishingAgent.WATER));
        tanks.add(1, new Tank(ExtinguishingAgent.FOAM));
    }

    public void setMixingRatio(int ratio) {
        this.foamRatio = ratio;
    }

    public int getLiquid(int amount) {
        int amountOfFoam = (amount * foamRatio) / 100;
        int amountOfWater = amount - amountOfFoam;
        amountOfWater -= tanks.get(0).getLiquid(amountOfWater);
        amountOfFoam -= tanks.get(1).getLiquid(amountOfFoam);
        if (amountOfWater * amountOfFoam > 0) {
            return 2;
        }
        amountOfWater -= amountOfFoam;
        if (amountOfWater != 0) {
            return amountOfWater / Math.abs(amountOfWater);
        }
        return 0;
    }
}
