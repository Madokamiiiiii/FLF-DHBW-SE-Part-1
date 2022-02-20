package airportfiretruck.cabin;


import airportfiretruck.centralunit.ISteeringCentralUnit;

// Note: Dieses Lenkrad erlaubt nur eine Wendung bis 90° in beide Richtungen.
public class SteeringWheel {
    private int position = 0;

    private ISteeringCentralUnit centralUnit;

    public int getPosition() {
        return position;
    }

    public void connectToCentralUnit(ISteeringCentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public void turnLeft(int degree) {
        if (position > -90) {
            position -= degree;
            centralUnit.steer(position);
        }
    }

    public void turnRight(int degree) {
        if (position < 90) {
            position += degree;
            centralUnit.steer(position);
        }
    }

    public ISteeringCentralUnit getCentralUnit() {
        return centralUnit;
    }
}
