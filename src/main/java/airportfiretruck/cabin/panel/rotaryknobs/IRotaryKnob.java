package airportfiretruck.cabin.panel.rotaryknobs;

public interface IRotaryKnob {
    ThrowerType getType();

    void turnLeft();

    void turnRight();
}
