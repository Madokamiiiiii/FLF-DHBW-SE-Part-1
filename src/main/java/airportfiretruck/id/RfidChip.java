package airportfiretruck.id;

import airportfiretruck.cabin.BusDoor;

public class RfidChip {
    private final byte[] encrypted;

    public RfidChip(byte[] secret) {
        this.encrypted = secret;
    }

    public byte[] getSecret() {
        return encrypted;
    }

    public void sendCode(BusDoor targetDoor) {
        targetDoor.unlock(this.encrypted);
    }
}
