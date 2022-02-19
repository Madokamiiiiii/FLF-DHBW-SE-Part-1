package airportfiretruck.cabin.seats;

public class Seat {
    private boolean isOccupied = false;

    private final Respirator respirator;

    public Seat(Respirator respirator) {
        this.respirator = respirator;
    }

    public Respirator getRespirator() {
        return respirator;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
