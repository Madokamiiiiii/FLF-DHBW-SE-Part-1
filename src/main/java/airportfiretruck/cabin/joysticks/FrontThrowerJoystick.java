package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.JoystickButton;
import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.FrontThrower;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;
import airportfiretruck.position.LeftRightSide;

import java.util.List;

public class FrontThrowerJoystick extends Joystick {

    private FrontThrower thrower;

    public FrontThrowerJoystick(List<PushButton> pushButtonList, JoystickButton joystickButton) {
        super(pushButtonList, joystickButton);
    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        if (pushButton.getPosition() == LeftRightSide.LEFT) {
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
    public void assign(IThrowerMixer thrower) {
        this.thrower = (FrontThrower) thrower;
    }

    public FrontThrower getThrower() {
        return thrower;
    }
}
