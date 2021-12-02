package airportfiretruck.cabin.panel.switches;

public abstract class Switch {
    private boolean isOn = false;

    public boolean isOn() {
        return isOn;
    }

    public void pressed() {
        isOn = !isOn;
    }

}
