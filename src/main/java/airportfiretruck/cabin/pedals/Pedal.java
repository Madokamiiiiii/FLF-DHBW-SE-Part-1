package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public class Pedal implements IPedal {
    private IPedalCentralUnit centralUnit;
    private final PedalType pedalType;

    public Pedal(PedalType pedalType) {
        this.pedalType = pedalType;
    }

    @Override
    public void pressed() {
        centralUnit.pedal(pedalType);
    }

    @Override
    public void connectToCentralUnit(IPedalCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }
}
