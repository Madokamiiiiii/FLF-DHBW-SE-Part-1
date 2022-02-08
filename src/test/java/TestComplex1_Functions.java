import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.BusDoor;
import airportfiretruck.person.Person;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestComplex1_Functions {
    AirportFireTruck flf;

    @BeforeEach
    public void init() {
        flf = new AirportFireTruck.Builder().build();
    }

    @Test
    @Order(1)
    public void TestSecretEncryption() {
        for (Person person : flf.getPersons()) {
            assertTrue(flf.getCentralUnit().validateCode(person.getIdCard().getRfidChip().getSecret()));
        }
    }

    @Test
    @Order(2)
    public void TestDoorLocker_Lock() {
        // unlock doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (bd.isLocked()) {
                bd.lock();
            }
        }
        flf.getCentralUnit().unLockDoors();
        // now the doors must be locked
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertTrue(bd.isLocked());
        }
    }

    @Test
    @Order(3)
    public void TestDoorLocker_Unlock() {
        // lock doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (!bd.isLocked()) {
                bd.lock();
            }
        }
        flf.getCentralUnit().unLockDoors();
        // now the doors must be unlocked
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(bd.isLocked());
        }
    }

    @Test
    @Order(4)
    public void TestDoorLocker_Lock_OpenDoor() {
        // unlock and open doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (bd.isLocked()) {
                bd.lock();
            }
            if (!bd.state()) {
                bd.openClose();
            }
        }
        flf.getCentralUnit().unLockDoors();
        // now the doors must be locked and closed
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertTrue(bd.isLocked());
            assertFalse(bd.state());
        }
    }

    @Test
    @Order(5)
    public void TestIdReader_Invalid() {
        assertFalse(flf.getCabin().getDoors().get(0).getIdReader().checkCode("asdf".getBytes()));
    }

    @Test
    @Order(6)
    public void TestIdReader_Valid() {
        for (Person person : flf.getPersons()) {
            assertTrue(flf.getCabin().getDoors().get(0).getIdReader().checkCode(person.getIdCard().getRfidChip().getSecret()));
        }
    }

    @Test
    @Order(7)
    public void TestUnlock() {
        // lock doors
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (!bd.isLocked()) {
                bd.lock();
            }
        }

        byte[] code = flf.getPersons().get(0).getIdCard().getRfidChip().getSecret();
        flf.getCabin().getDoors().get(0).unlock(code);
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(bd.isLocked());
        }
    }

    @Test
    @Order(8)
    public void TestIntegration() {
        // unlock doors, open door 1
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (bd.isLocked()) {
                bd.lock();
            }
        }
        flf.getCabin().getDoors().get(1).openClose();

        flf.getPersons().get(0).useCard(flf.getCabin().getDoors().get(0));
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(bd.state());
            assertTrue(bd.isLocked());
        }
    }
}
