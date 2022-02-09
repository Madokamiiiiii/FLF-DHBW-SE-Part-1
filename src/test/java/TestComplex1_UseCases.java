import airportfiretruck.AirportFireTruck;
import airportfiretruck.buttons.BusDoorButton;
import airportfiretruck.cabin.BusDoor;
import airportfiretruck.position.FrontRearSide;
import komplexaufgabe1.Complex1AirportFireTruck;
import komplexaufgabe1.ComplexBusDoor;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestComplex1_UseCases {
    AirportFireTruck flf;

    @BeforeEach
    public void init() {
        flf = new Complex1AirportFireTruck.Builder().build();
    }

    @Test
    @Order(1)
    public void UseCase1() {
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
            if (bd.state()) {
                bd.openClose();
            }
        }

        // Fahrer und Operator sitzen im FLF, die Türen sind nicht gesperrt, aber geschlossen:
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(((ComplexBusDoor) bd).isLocked());
            assertFalse(bd.state());
        }

        // Operator öffnet von innen rechte Bustür über Taster
        flf.getCabin().getDoors().get(1).getButtons().stream().filter(x -> x.getPosition() == FrontRearSide.REAR).forEach(BusDoorButton::pressed);

        // Somit ist die rechte Tür (1) geöffnet, die linke (0) nicht; beide sind nicht gesperrt
        assertFalse(((ComplexBusDoor) flf.getCabin().getDoors().get(0)).isLocked());
        assertFalse(flf.getCabin().getDoors().get(0).state());
        assertFalse(((ComplexBusDoor) flf.getCabin().getDoors().get(1)).isLocked());
        assertTrue(flf.getCabin().getDoors().get(1).state());

        // Operator verlässt Fahrzeug

        // Fahrer öffnet von innen linke Bustür über Taster
        flf.getCabin().getDoors().get(0).getButtons().stream().filter(x -> x.getPosition() == FrontRearSide.REAR).forEach(BusDoorButton::pressed);

        // Nun sind beide Türen offen, aber nicht gesperrt
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(((ComplexBusDoor) bd).isLocked());
            assertTrue(bd.state());
        }

        // Fahrer verlässt Fahrzeug

        // Fahrer hält ID-Karte vor linken Sensor (Fahrer ist hier Bob)
        ((Complex1AirportFireTruck) flf).getPersons().stream().filter(x -> x.getName().equals("Bob")).forEach(x -> x.useCard((ComplexBusDoor) flf.getCabin().getDoors().get(0)));

        // Beide Türen sind nun geschlossen und gesperrt
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertTrue(((ComplexBusDoor) bd).isLocked());
            assertFalse(bd.state());
        }
    }

    @Test
    @Order(2)
    public void UseCase2() {
        for (BusDoor bd : flf.getCabin().getDoors()) {
            if (!((ComplexBusDoor) bd).isLocked()) {
                ((ComplexBusDoor) bd).lock();
            }
            if (bd.state()) {
                bd.openClose();
            }
        }
        // Das FLF ist leer, die Türen sind gesperrt und geschlossen:
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertTrue(((ComplexBusDoor) bd).isLocked());
            assertFalse(bd.state());
        }

        // Fahrer hält ID-Karte vor linken Sensor (Fahrer ist hier Red Adair)
        ((Complex1AirportFireTruck) flf).getPersons().stream().filter(x -> x.getName().equals("Red Adair")).forEach(x -> x.useCard((ComplexBusDoor) flf.getCabin().getDoors().get(0)));

        // Beide Türen sind nun geöffnet und nicht gesperrt
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(((ComplexBusDoor) bd).isLocked());
            assertTrue(bd.state());
        }

        // Operator setzt sich und schließt von innen die rechte Bustür über Taster
        flf.getCabin().getDoors().get(1).getButtons().stream().filter(x -> x.getPosition() == FrontRearSide.REAR).forEach(BusDoorButton::pressed);

        // Somit ist die linke Tür (0) geöffnet, die rechte (1) nicht; beide sind nicht gesperrt
        assertFalse(((ComplexBusDoor) flf.getCabin().getDoors().get(0)).isLocked());
        assertTrue(flf.getCabin().getDoors().get(0).state());
        assertFalse(((ComplexBusDoor) flf.getCabin().getDoors().get(1)).isLocked());
        assertFalse(flf.getCabin().getDoors().get(1).state());

        // Fahrer setzt sich und schließt von innen linke Bustür über Taster
        flf.getCabin().getDoors().get(0).getButtons().stream().filter(x -> x.getPosition() == FrontRearSide.REAR).forEach(BusDoorButton::pressed);

        // Nun sind beide Türen geschlossen, aber nicht gesperrt
        for (BusDoor bd : flf.getCabin().getDoors()) {
            assertFalse(((ComplexBusDoor) bd).isLocked());
            assertFalse(bd.state());
        }
    }
}
