package airportfiretruck.cabin.joysticks;

public class RoofThrowerJoystick implements IJoystick{
    public RoofThrowerJoystick() {

    }

    @Override
    public void pushButtonPressed(PushButton pushButton) {
        if (pushButton.getPosition() == ButtonPosition.LEFT) {
            if (pushButton.isActive()) {        // RoofThrower ist deaktiviert und schwenken auf 0°
                thrower.setActive(false);
                thrower.setLowerSegmentDegree(0);
                thrower.setUpperSegmentLength(0);
            } else {                            // RoofThrower ist aktiviert und schwenken auf 90°
                thrower.setActive(true);
                thrower.setLowerSegmentDegree(90);
                thrower.setUpperSegmentLength(11);
            }
        } else {                                // Rechter Druckknopf
            if (thrower.isActive()) {           // Ändere Mischverhältnis
                next();
            }                                   // Ansonsten tue nichts
        }
    }

    @Override
    public void assignThrower(IThrowerMixer thrower) {
        this.thrower = (RoofThrower) thrower;
    }

    @Override
    public void joystickButtonPressed() {
        joystickButtonPressed(thrower);
    }

}
