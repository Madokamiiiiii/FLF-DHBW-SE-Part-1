package airportfiretruck.cabin.joysticks;

import airportfiretruck.buttons.PushButton;

public interface IJoystick {

    void pushButtonPressed(PushButton pushButton);

    void joystickButtonPressed();
}
