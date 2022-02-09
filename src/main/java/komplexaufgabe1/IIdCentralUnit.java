package komplexaufgabe1;

import airportfiretruck.centralunit.IBaseCentralUnit;

public interface IIdCentralUnit extends IBaseCentralUnit {
    boolean validateCode(byte[] code);
}
