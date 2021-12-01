package airportfiretruck.buttons;

public abstract class Button {
    protected ButtonPosition position;

    public void pressed() {

    }

    public void setPosition(ButtonPosition position) {
        this.position = position;
    }

    public ButtonPosition getPosition() {
        return position;
    }
}
