package airportfiretruck.extinguisher.thrower;

public interface IThrowerMixer extends IThrower {

    void setMixingRatio(int ratio);

    boolean isActive();

    void setActive(boolean active);
}
