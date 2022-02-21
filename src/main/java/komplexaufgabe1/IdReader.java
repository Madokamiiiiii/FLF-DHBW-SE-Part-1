package komplexaufgabe1;

import airportfiretruck.cabin.BusDoor;

public class IdReader {
    private final BusDoor busDoor;
    private IIdCentralUnit centralUnit;

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
