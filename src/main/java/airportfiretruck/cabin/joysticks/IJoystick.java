package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public interface IJoystick {

    void pushButtonPressed(PushButton pushButton);

    void joystickButtonPressed();

    void assign(IThrowerMixer thrower);
}
