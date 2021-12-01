package airportfiretruck.centralunit;

import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;

public interface IThrowerCentralUnit extends IBaseCentralUnit {
    void thrower(IRotaryKnob rotaryKnob);
}
