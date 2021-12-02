package airportfiretruck.cabin.seats;

public class FrontSeat extends Seat {

    private boolean isOccupied;

    public FrontSeat() {
        isOccupied = false;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
