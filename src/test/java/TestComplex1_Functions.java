import airportfiretruck.cabin.BusDoor;
import komplexaufgabe1.Complex1AirportFireTruck;
import komplexaufgabe1.ComplexBusDoor;
import komplexaufgabe1.ComplexCentralUnit;
import komplexaufgabe1.Person;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestComplex1_Functions {
    Complex1AirportFireTruck flf;

    @BeforeEach
    public void init() {
        flf = (Complex1AirportFireTruck) new Complex1AirportFireTruck.Builder().build();
    }

    @Test
    @Order(1)
    public void TestSecretEncryption() {
        for (Person person : flf.getPersons()) {
            assertTrue(((ComplexCentralUnit) flf.getCentralUnit()).validateCode(person.getIdCard().getRfidChip().getSecret()));
        }
    }

    @Test
    @Order(2)
    public void TestDoorLocker_Lock() {
        // unlock doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
        }
        ((ComplexCentralUnit) flf.getCentralUnit()).unLockDoors();
        // now the doors must be locked
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertTrue(((ComplexBusDoor) bd).isLocked());
        }
    }

    @Test
    @Order(3)
    public void TestDoorLocker_Unlock() {
        // lock doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (!((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
        }
        ((ComplexCentralUnit) flf.getCentralUnit()).unLockDoors();
        // now the doors must be unlocked
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(((ComplexBusDoor) bd).isLocked());
        }
    }

    @Test
    @Order(4)
    public void TestDoorLocker_Lock_OpenDoor() {
        // unlock and open doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
            if (!bd.isOpen()) {
                bd.openClose();
            }
        }
        ((ComplexCentralUnit) flf.getCentralUnit()).unLockDoors();
        // now the doors must be locked and closed
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertTrue(((ComplexBusDoor) bd).isLocked());
            assertFalse(bd.isOpen());
        }
    }

    @Test
    @Order(5)
    public void TestIdReader_Invalid() {
        assertFalse(((ComplexBusDoor) flf.getCabin().getDoors().get(0)).getIdReader().checkCode("asdf".getBytes()));
    }

    @Test
    @Order(6)
    public void TestIdReader_Valid() {
        for (Person person : flf.getPersons()) {
            assertTrue(((ComplexBusDoor) flf.getCabin().getDoors().get(0)).getIdReader().checkCode(person.getIdCard().getRfidChip().getSecret()));
        }
    }

    @Test
    @Order(7)
    public void TestUnlock() {
        // lock doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (!((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
        }

        byte[] code = flf.getPersons().get(0).getIdCard().getRfidChip().getSecret();
        ((ComplexBusDoor) flf.getCabin().getDoors().get(0)).unlock(code);
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(((ComplexBusDoor) bd).isLocked());
        }
    }

    @Test
    @Order(8)
    public void TestIntegration() {
        // unlock doors, open door 1
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
        }
        flf.getCabin().getDoors().get(1).openClose();

        flf.getPersons().get(0).useCard((ComplexBusDoor) flf.getCabin().getDoors().get(0));
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(bd.isOpen());
            assertTrue(((ComplexBusDoor) bd).isLocked());
        }
    }
}
