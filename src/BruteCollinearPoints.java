import java.util.Arrays;

/**
 * that examines 4 points at a time and checks whether they all lie on the same line segment,
 * returning all such line segments.
 * To check whether the 4 points p, q, r, and s are collinear,
 * check whether the three slopes between p and q, between p and r, and between p and s are all equal.
 */
public class BruteCollinearPoints {
    private int numSegments;
    private LineSegment[] lines;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }


        // deep copy
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            pointsCopy[i] = points[i];
        }

        // check duplicates
        for (int i = 1; i < pointsCopy.length; i++) {
            if (pointsCopy[i] == pointsCopy[i-1]) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(pointsCopy);
        LineSegment[] tempLines = new LineSegment[points.length];
        numSegments = 0;
        for (int i = 0; i < pointsCopy.length; i++) {
            for (int j = i + 1; j < pointsCopy.length; j++) {
                for (int k = j + 1; k < pointsCopy.length; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[j].slopeTo(pointsCopy[k]) &&
                                pointsCopy[j].slopeTo(pointsCopy[k]) == pointsCopy[k].slopeTo(pointsCopy[l])) {
                            tempLines[numSegments] = new LineSegment(pointsCopy[i], pointsCopy[l]);
                            numSegments++;
                        }
                    }
                }
            }
        }

        lines = Arrays.copyOf(tempLines, numSegments);

    }

    // the number of line segments
    public int numberOfSegments() {
        return numSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] res = new LineSegment[numSegments];
        for (int i = 0; i < numSegments; i++) {
            res[i] = lines[i];
        }
        return res;
    }
}
