package airportfiretruck.thrower;

public class FrontThrower implements IThrowerMixer {
    private int degree;
    private int level;

    public FrontThrower() {

    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void spray() {

    }
}
