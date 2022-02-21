package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.PushButton;
import airportfiretruck.extinguisher.thrower.IThrowerMixer;

public interface IJoystick<T extends IThrowerMixer> {

    void pushButtonPressed(PushButton pushButton);

    void joystickButtonPressed();

    void assign(T thrower);
}
