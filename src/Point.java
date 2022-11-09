import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point>  {
    private final int x;
    private final int y;
    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    // Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
    // if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
    public int compareTo(Point that) {
        if ((this.y < that.y) || ((this.y == that.y) && (this.x < that.x))) {
            return -1;
        } else if (((this.y == that.y) && (this.x == that.x))) {
            return 0;
        } else {
            return 1;
        }
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (this.y == that.y) {
            if (this.x == that.x) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return 0.0;
            }
        } else {
            if (this.x == that.x) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return (double) (this.y - that.y) / (this.x - that.x);
            }
        }
    }

    // compare two points by slopes they make with this point
    // Formally, the point (x1, y1) is less than the point (x2, y2)
    // if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
    // Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
    private class BySlope implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            if (slope1 == slope2) {
                return 0;
            } else if (slope1 < slope2) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public Comparator<Point> slopeOrder() {
        return new Point.BySlope();
    }

    public static void main(String[] args) {
        Point x = new Point(10, 0);
        Point y = new Point(0, 0);
        x.drawTo(y);
    }

}
