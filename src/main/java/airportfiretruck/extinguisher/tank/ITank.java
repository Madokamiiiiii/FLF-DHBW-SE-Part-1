package airportfiretruck.extinguisher.tank;

public interface ITank {
    int getLiquid(int amount);

    ExtinguishingAgent getType();

    int getRemainingCapacity();

    void fill(int amount);
}
