package airportfiretruck.id;

import airportfiretruck.cabin.BusDoor;
import airportfiretruck.person.Person;

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

    public void use(BusDoor targetDoor) {
        this.rfidChip.sendCode(targetDoor);
    }
}
