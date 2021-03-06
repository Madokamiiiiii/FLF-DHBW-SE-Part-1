package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public class Pedal implements IPedal {
    private final PedalType pedalType;
    private IPedalCentralUnit centralUnit;

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

    public IPedalCentralUnit getCentralUnit() {
        return centralUnit;
    }

    public PedalType getPedalType() {
        return pedalType;
    }
}
