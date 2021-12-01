package airportfiretruck.thrower;

import java.util.List;

public class UpperSegment {
    private final List<SegmentPart> segmentParts;

    public UpperSegment() {
        segmentParts = List.of(new SegmentPart(), new SegmentPart(), new SegmentPart());
    }

    public void setLength(int length) {
        for (SegmentPart segmentPart : segmentParts) {
            segmentPart.setLength(length);
        }
    }
}
