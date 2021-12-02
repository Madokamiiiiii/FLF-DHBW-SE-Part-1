package airportfiretruck.cabin;


import airportfiretruck.centralunit.ISteeringCentralUnit;

public class SteeringWheel {
    private int position;

    private ISteeringCentralUnit centralUnit;

    public SteeringWheel() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void connectToCentralUnit(ISteeringCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public void turnLeft(int degree) {
        if (position > -40) {
            position -= degree;
            centralUnit.steer();
        }
    }

    public void turnRight(int degree) {
        if (position < 40) {
            position += degree;
            centralUnit.steer();
        }
    }
}
