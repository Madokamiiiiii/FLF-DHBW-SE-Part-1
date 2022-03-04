package airportfiretruck.extinguisher;

public abstract class Thrower implements IThrower {

    protected final int limit;

    protected Thrower(int limit) {
        this.limit = limit;
    }
}
