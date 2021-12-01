package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.PushButton;
import airportfiretruck.thrower.IThrowerMixer;

public interface IJoystick {

    void pushButtonPressed(PushButton pushButton);

    void joystickButtonPressed();

    void assignThrower(IThrowerMixer thrower);
}
