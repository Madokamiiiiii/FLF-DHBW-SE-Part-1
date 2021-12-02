package airportfiretruck.cabin;


import airportfiretruck.centralunit.ISteeringCentralUnit;

public class SteeringWheel {
    private int position;

    private final ISteeringCentralUnit centralUnit;

    public SteeringWheel(ISteeringCentralUnit centralUnit) {
        position = 0;
        this.centralUnit = centralUnit;
    }

    public int getPosition() {
        return position;
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
