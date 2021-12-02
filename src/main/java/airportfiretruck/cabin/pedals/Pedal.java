package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public class Pedal implements IPedal {
    private IPedalCentralUnit centralUnit;
    private PedalType pedalType;

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
