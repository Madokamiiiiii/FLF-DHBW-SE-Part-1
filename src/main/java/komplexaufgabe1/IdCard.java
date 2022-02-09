package komplexaufgabe1;

public class IdCard {
    private final RfidChip rfidChip;
    private final Person person;

    public IdCard(Person person, RfidChip rfidChip) {
        this.person = person;
        this.rfidChip = rfidChip;
    }

    public RfidChip getRfidChip() {
        return rfidChip;
    }

    public void use(ComplexBusDoor targetDoor) {
        this.rfidChip.sendCode(targetDoor);
    }
}
