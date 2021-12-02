package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public class Pedal implements IPedal {
    private final IPedalCentralUnit centralUnit;
    private final PedalType pedalType;

    public Pedal(PedalType type, IPedalCentralUnit centralUnit) {
        pedalType = type;
        this.centralUnit = centralUnit;
    }

    @Override
    public void pressed() {
        if (pedalType == PedalType.GAS) {
            centralUnit.pedal(pedalType);
        }
    }
}
