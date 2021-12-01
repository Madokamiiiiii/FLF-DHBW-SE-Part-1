package airportfiretruck.thrower;

public interface IThrowerMixer extends IThrower {

    void setMixingRatio(int ratio);

    void assignJoystick();

    void setActive(boolean active);

    boolean isActive();
}
