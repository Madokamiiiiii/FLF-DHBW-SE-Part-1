package airportfiretruck.cabin.panel.switches;

public abstract class Switch {
    private boolean isOn;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void pressed() {
        setOn(!isOn());
    }
}
