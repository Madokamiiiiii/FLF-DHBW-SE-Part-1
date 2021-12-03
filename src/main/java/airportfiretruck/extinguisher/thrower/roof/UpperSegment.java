package airportfiretruck.extinguisher.thrower.roof;

import java.util.List;

public class UpperSegment {
    private final List<SegmentPart> segmentParts;

    public UpperSegment() {
        segmentParts = List.of(new SegmentPart(6),
                new SegmentPart(6),
                new SegmentPart(5));
    }

    public void setLength(int length) {
        if (length == 0) {
            for (SegmentPart segmentPart : segmentParts) {
                segmentPart.setLength(0);
            }
        } else {
            for (SegmentPart segmentPart : segmentParts) {
                length = segmentPart.setLength(length);
                if (length == 0) {
                    return;
                }
            }
        }
    }

    public List<SegmentPart> getSegmentParts() {
        return segmentParts;
    }
}
