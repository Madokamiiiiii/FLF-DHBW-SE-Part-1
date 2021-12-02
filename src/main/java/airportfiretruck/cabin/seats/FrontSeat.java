package airportfiretruck.cabin.seats;

import airportfiretruck.position.LeftRightSide;

public class FrontSeat extends Seat {

    private boolean isOccupied = false;
    private final LeftRightSide leftRightSide;

    public FrontSeat(Respirator respirator, LeftRightSide leftRightSide) {
        super(respirator);
        this.leftRightSide = leftRightSide;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
