package airportfiretruck.centralunit;

import airportfiretruck.cabin.pedals.PedalType;

public interface IPedalCentralUnit extends IBaseCentralUnit{
    public void pedal(PedalType ptype);
}
