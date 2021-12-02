package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public interface IPedal {

    void pressed();

    void connectToCentralUnit(IPedalCentralUnit centralUnit);
}
