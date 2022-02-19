import airportfiretruck.AirportFireTruck;
import org.junit.jupiter.api.*;

import static airportfiretruck.position.Position.BOTTOM;
import static airportfiretruck.position.Position.TOP;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestParking {
    private AirportFireTruck airportFireTruck;

    @BeforeEach
    public void init() {
        // is parking already
        airportFireTruck = new AirportFireTruck.Builder().build();
        parkFLF();
    }

    @Test
    @Order(1)
    public void enginesOff() {
        airportFireTruck.getEngines().forEach(engine -> assertFalse(engine.isOn()));
    }

    @Test
    @Order(2)
    public void seatsEmpty() {
        airportFireTruck.getCabin().getSeats().forEach(seat -> assertFalse(seat.isOccupied()));
    }

    @Test
    @Order(3)
    public void doorsOpen() {
        airportFireTruck.getCabin().getDoors().forEach(door -> assertTrue(door.isOpen()));
    }

    @Test
    @Order(4)
    public void throwers() {
        // Roof Thrower
        assertEquals(0, airportFireTruck.getRoofThrower().getLowerSegment().getDegree());
        assertEquals(0, airportFireTruck.getRoofThrower().getUpperSegment().getLength());

        // Front Thrower
        assertFalse(airportFireTruck.getFrontThrower().isActive());
    }

    @Test
    @Order(5)
    public void lightsOff() {
        // Warning Lights
        airportFireTruck.getWarningLights().forEach(warningLight -> assertFalse(warningLight.isOn()));

        // Blue Lights
        airportFireTruck.getBlueLights().forEach(blueLight -> assertFalse(blueLight.isOn()));

        // Front & Roof Lights
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(TOP)).forEach(headLight -> assertFalse(headLight.isOn()));
        airportFireTruck.getHeadLights().stream().filter(headLight -> headLight.getPosition().equals(BOTTOM)).forEach(headLight -> assertFalse(headLight.isOn()));

        // Side Lights
        airportFireTruck.getSideLights().forEach(sideLight -> assertFalse(sideLight.isOn()));
    }

    @Test
    @Order(6)
    public void tank() {
        //airportFireTruck.getRoofThrower().getMixer().getTanks().stream().filter(tank -> tank.getType() == ExtinguishingAgent.WATER)
    }

    private void parkFLF() {
        airportFireTruck.getCabin().getDoors().forEach(door -> {
            if (!door.isOpen()) {
                door.openClose();
            }
        });
    }
}
