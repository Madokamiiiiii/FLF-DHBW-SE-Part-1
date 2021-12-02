package airportfiretruck.extinguisher.thrower;

public abstract class Thrower implements IThrower {

    protected int limit;

    public Thrower(int limit) {
        this.limit = limit;
    }
}
