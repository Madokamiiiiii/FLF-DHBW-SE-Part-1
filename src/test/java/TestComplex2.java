import airportfiretruck.AirportFireTruck;
import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.position.LeftRightSide;
import komplexaufgabe2.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Small test class for Komplexaufgabe 2.
public class TestComplex2 {

    private AirportFireTruck airportFireTruck;

    @BeforeEach
    public void init() {
        // Generate FLF
        airportFireTruck = new AirportFireTruck.Builder().build();
    }

    @Test
    void test() {
        // Initialize
        PushButton pushButton = new PushButton(LeftRightSide.MIDDLE);
        JoystickButton joystickButton = new JoystickButton();
        FrontThrower frontThrower = airportFireTruck.getFrontThrower();
        IntelligentFrontThrowerJoystick intelligentFrontThrowerJoystick = new IntelligentFrontThrowerJoystick(pushButton, joystickButton);
        pushButton.setJoystick(intelligentFrontThrowerJoystick);
        joystickButton.setJoystick(intelligentFrontThrowerJoystick);
        intelligentFrontThrowerJoystick.assign(frontThrower);

        pushButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Active);
        Assertions.assertTrue(frontThrower.isActive());

        joystickButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Mix3);

        joystickButton.pressed();
        joystickButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Mix10);

        pushButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Final);

        joystickButton.pressed();   // spray()
        Assertions.assertEquals(10, frontThrower.getMixingRatio());

        pushButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Mix10);

        joystickButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Mix0);

        joystickButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Active);

        pushButton.pressed();
        Assertions.assertTrue(intelligentFrontThrowerJoystick.getState() instanceof Inactive);

    }
}