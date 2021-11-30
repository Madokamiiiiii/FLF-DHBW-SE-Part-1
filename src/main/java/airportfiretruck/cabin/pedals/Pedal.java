package airportfiretruck.cabin.pedals;

import airportfiretruck.centralunit.IPedalCentralUnit;

public class Pedal implements IPedal{
    private IPedalCentralUnit cunit;
    private PedalType ptype;
    public Pedal() {

    }

    @Override
    public void pressed() {
        if (ptype == PedalType.GAS) {
            cunit.pedal(ptype);
        }
    }
}
