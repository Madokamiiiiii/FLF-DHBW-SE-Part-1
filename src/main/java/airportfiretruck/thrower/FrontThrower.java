package airportfiretruck.thrower;

public class FrontThrower extends ThrowerMixer {
    private int degree;
    private int level;

    public FrontThrower() {
        joystick.assignThrower(this);
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void spray() {
        // same here: evaluate??
        mixer.getLiquid(level);
    }
}
