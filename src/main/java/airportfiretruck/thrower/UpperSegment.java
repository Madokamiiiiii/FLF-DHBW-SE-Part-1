package airportfiretruck.thrower;

import java.util.List;

public class UpperSegment {
    private final List<SegmentPart> segmentParts;

    public UpperSegment() {
        segmentParts = List.of(new SegmentPart(6), new SegmentPart(6), new SegmentPart(5));
    }

    public void setLength(int length) {
        for (SegmentPart segmentPart : segmentParts) {
            segmentPart.setLength(length);
        }
    }
}
