package komplexaufgabe1;

import airportfiretruck.cabin.BusDoor;

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
