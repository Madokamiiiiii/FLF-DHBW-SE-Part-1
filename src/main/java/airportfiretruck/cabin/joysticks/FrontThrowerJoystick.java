package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.ButtonPosition;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.thrower.FrontThrower;
import airportfiretruck.thrower.IThrowerMixer;

public class FrontThrowerJoystick extends Joystick {
    public FrontThrowerJoystick() {
        super();
    }

    public FrontThrower thrower;

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

    @Override
    public void assignThrower(IThrowerMixer thrower) {
        this.thrower = (FrontThrower) thrower;
    }


}
