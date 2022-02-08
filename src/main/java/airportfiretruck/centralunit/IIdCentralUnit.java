package airportfiretruck.centralunit;

public interface IIdCentralUnit extends IBaseCentralUnit {
    boolean validateCode(byte[] code);
}
