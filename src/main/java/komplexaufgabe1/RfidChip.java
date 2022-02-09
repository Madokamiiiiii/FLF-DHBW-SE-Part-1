package komplexaufgabe1;

public class RfidChip {
    private final byte[] encrypted;

    public RfidChip(byte[] secret) {
        this.encrypted = secret;
    }

    public byte[] getSecret() {
        return encrypted;
    }

    public void sendCode(ComplexBusDoor targetDoor) {
        targetDoor.unlock(this.encrypted);
    }
}
