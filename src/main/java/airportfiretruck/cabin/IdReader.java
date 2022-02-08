package airportfiretruck.cabin;

import airportfiretruck.centralunit.IIdCentralUnit;

public class IdReader {
    private IIdCentralUnit centralUnit;
    private final BusDoor busDoor;

    public IdReader(BusDoor busDoor) {
        this.busDoor = busDoor;
    }

    public void connectToCentralUnit(IIdCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public boolean checkCode(byte[] code) {
        return this.centralUnit.validateCode(code);
    }
}
