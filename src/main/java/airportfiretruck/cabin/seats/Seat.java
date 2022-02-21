package airportfiretruck.cabin.seats;

public class Seat {
    private final Respirator respirator;
    private boolean isOccupied = false;

    public Seat(Respirator respirator) {
        this.respirator = respirator;
    }

    public Respirator getRespirator() {
        return respirator;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
