package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.ButtonPosition;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.FrontThrower;

public class FrontThrowerJoystick extends Joystick {
    public FrontThrower thrower;

    public FrontThrowerJoystick(FrontThrower thrower) {
        super();
        this.thrower = thrower;
    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        if (pushButton.getPosition() == ButtonPosition.LEFT) {
            if (pushButton.isActive()) {        // Frontwerfer ist deaktiviert und schwenken auf 0°
                thrower.setActive(false);
                thrower.setDegree(0);
            } else {                            // Frontwerfer ist aktiviert und schwenken auf 90°
                thrower.setActive(true);
                thrower.setDegree(90);
            }
        } else {                                // Rechter Druckknopf
            if (thrower.isActive()) {           // Ändere Mischverhältnis
                next();
            }                                   // Ansonsten tue nichts
        }
    }

    @Override
    public void joystickButtonPressed() {
        joystickButtonPressed(thrower);
    }

}
