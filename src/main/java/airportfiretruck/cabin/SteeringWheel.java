package airportfiretruck.cabin;


import airportfiretruck.centralunit.ISteeringCentralUnit;

public class SteeringWheel {
    private int position;
    private ISteeringCentralUnit cunit;

    public SteeringWheel() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void turnLeft(int degree) {
        if (position > -40) {
            position -= degree;
            cunit.steer();
        }
    }
    public void turnRight(int degree) {
        if (position < 40) {
            position += degree;
            cunit.steer();
        }
    }
}
