package airportfiretruck.cabin.seats;

import airportfiretruck.position.LeftRightSide;

public class FrontSeat extends Seat {
    private final LeftRightSide leftRightSide;

    public FrontSeat(Respirator respirator, LeftRightSide leftRightSide) {
        super(respirator);
        this.leftRightSide = leftRightSide;
    }

    public LeftRightSide getLeftRightSide() {
        return leftRightSide;
    }
}
