package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public class Pedal implements IPedal {
    private final IPedalCentralUnit centralUnit;
    private final PedalType pedalType;

    public Pedal(PedalType pedalType) {
        this.pedalType=pedalType;
    }

    @Override
    public void pressed() {
        if (pedalType == PedalType.GAS) {
            centralUnit.pedal(pedalType);
        }
    }
    public void connectToCentralUnit(IPedalCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }
}
