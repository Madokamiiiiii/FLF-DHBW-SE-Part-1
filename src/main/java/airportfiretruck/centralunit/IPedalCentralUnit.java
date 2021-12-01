package airportfiretruck.centralunit;

import airportfiretruck.cabin.pedals.PedalType;

public interface IPedalCentralUnit extends IBaseCentralUnit {
    void pedal(PedalType pedalType);
}
