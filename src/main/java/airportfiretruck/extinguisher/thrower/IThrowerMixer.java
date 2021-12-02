package airportfiretruck.extinguisher.thrower;

public interface IThrowerMixer extends IThrower {

    void setMixingRatio(int ratio);

    void setActive(boolean active);

    boolean isActive();
}
