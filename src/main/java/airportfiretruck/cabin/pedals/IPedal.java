package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public interface IPedal {
    IPedalCentralUnit cunit = null;
    PedalType ptype = null;

    public void pressed();
}
