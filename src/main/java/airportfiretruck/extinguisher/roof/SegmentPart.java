package airportfiretruck.extinguisher.roof;

public class SegmentPart {
    private final int maxLength;
    private int length;

    public SegmentPart(int length) {
        this.maxLength = length;
    }

    public int setLength(int length) {
        if (length <= maxLength) {
            this.length = length;
            return 0;
        } else {
            this.length = maxLength;
            length -= maxLength;
            return length;
        }
    }

    public int getLength() {
        return length;
    }
}
