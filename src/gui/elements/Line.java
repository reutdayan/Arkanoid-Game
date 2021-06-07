package gui.elements;

/**
 * @author Reut Dayan
 * @version 30.3.21
 * Gui.Line class with a start and end points.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start - the start point of the line
     * @param end   - the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 - the x coordinate of the start point of the line
     * @param y1 - the y coordinate of the start point of the line
     * @param x2 - the x coordinate of the end point of the line
     * @param y2 - the y coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * length.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle.
     *
     * @return Returns the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * accessors start.
     *
     * @return the start point of this line
     */
    public Point start() {
        return this.start;
    }

    /**
     * accessors end.
     *
     * @return the end point of this line
     */
    public Point end() {
        return this.end;
    }

    /**
     * calcSlope.
     *
     * @return the slope of the line (m in y=ma+b)
     */
    public double calcSlope() {
        //Vertical line to x axis
        if (this.end.getX() == this.start.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        // m = y2-y1 / x2-x1
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * calcIntercept.
     *
     * @return the intercept of the line with y-axis (b in y=mx+b)
     */
    public double calcIntercept() {
        double m = this.calcSlope();
        //Vertical line to x axis
        if (m == Double.POSITIVE_INFINITY) {
            return 0;
        }
        // b=y1-mx1
        return (this.start.getY() - m * this.start.getX());
    }

    /**
     * isIntersecting.
     *
     * @param other - the line we will check whether he and this line have a point of intersection
     * @return - true if the lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * isBetweenXRange.
     *
     * @param xIntersect the method checks if xIntersects is in the range of this line
     * @return - true if xIntersect is in the range of the line, and false otherwise.
     */
    public boolean isBetweenXRange(double xIntersect) {
        double startX = Math.min(this.start.getX(), this.end().getX());
        double endX = Math.max(this.start.getX(), this.end().getX());
        return (xIntersect >= startX && xIntersect <= endX);
    }

    /**
     * isBetweenYRange.
     *
     * @param y - he method checks if y is in the range of this line
     * @return -true if y is in the range of the line, and false otherwise.
     */
    public boolean isBetweenYRange(double y) {
        double startY = Math.min(this.start.getY(), this.end().getY());
        double endY = Math.max(this.start.getY(), this.end().getY());
        return (y >= startY && y <= endY);
    }

    /**
     * isVertical.
     *
     * @return - true if the line is vertical to x axis and false otherwise.
     */
    public boolean isVertical() {
        return (this.end.getX() == this.start.getX());
    }

    /**
     * intersectWithVerticalLine.
     * the method calculate the intersect point between lineVertical and line when lineVertical is vertical to x axis
     *
     * @param lineVertical - the vertical line
     * @param line         - a line
     * @return - the point of the intersect if exists and null otherwise.
     */
    private static Point intersectWithVerticalLine(Line lineVertical, Line line) {
        double x = lineVertical.start.getX();
        double y = line.calcSlope() * x + line.calcIntercept();
        if ((lineVertical.isBetweenYRange(y)) && (line.isBetweenXRange(x))) {
            return (new Point(x, y));
        }
        return null;
    }

    /**
     * intersectionWith.
     *
     * @param other - the line we will calculate the intersection point with this line.
     * @return - if the lines intersect returns the point of the intersection, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // Check vertical case
        if (this.isVertical()) {
            //the two lines are vertical
            if (other.isVertical()) {
                //check the case when there is one intersect point
                if (this.start.getX() == other.start.getX()) {
                    if (this.equals(other)) {
                        return null;
                    }
                    if (this.start.getY() == other.start().getY() || this.start.getY() == other.end().getY()) {
                        return new Point(this.start.getX(), this.start.getY());
                    }
                    if (this.end.getY() == other.start().getY() || this.end.getY() == other.end().getY()) {
                        return new Point(this.end.getX(), this.end.getY());
                    }
                }
                return null;
            } else {
                //only this line is vertical
                return intersectWithVerticalLine(this, other);
            }
        } else if (other.isVertical()) {
            //only other line is vertical
            return intersectWithVerticalLine(other, this);
        }
        // the lines have the same slope.
        if (other.calcSlope() == this.calcSlope()) {
            //check the case where there is one intersect point
            if (this.calcIntercept() == other.calcIntercept()) {
                if (this.start.getX() == other.start().getX() || this.start.getX() == other.end().getX()) {
                    return (new Point(this.start.getX(), this.start.getY()));
                }
                if (this.end.getX() == other.start().getX() || this.end.getX() == other.end().getX()) {
                    return (new Point(this.end.getX(), this.end.getY()));
                }
            }
            //there is no intersection point.
            return null;
        }
        double slopeDifference = other.calcSlope() - this.calcSlope();
        // calc the x coordinate of the intersection between the 2 lines.
        double xIntersect = (this.calcIntercept() - other.calcIntercept()) / slopeDifference;
        // checks if xIntersect is in the range of the 2 lines
        if ((this.isBetweenXRange(xIntersect)) && (other.isBetweenXRange(xIntersect))) {
            // calc the y coordinate of the intersection
            double yIntersect = this.calcSlope() * xIntersect + this.calcIntercept();
            return new Point(xIntersect, yIntersect);
        }
        // there is no intersect point between the lines.
        return null;
    }

    /**
     * equals.
     *
     * @param other - lhe line we will check if it equals to this line.
     * @return -true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end))
                || (this.start.equals(other.end()) && this.end.equals(other.start()));
    }

    /**
     * closestIntersectionToStartOfLine.
     * the methode will check if this line and the rectangle has an intersection point-
     * if so, return it, otherwise- return null.
     *
     * @param rect - the rectangle we will check.
     * @return - return the intersection point between this line and the rectangle if doesn't exists return null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        // iterate throw intersectionPoint and check which iteration point
        // is the closest to the start point of this line
        for (Point p : intersectionPoints) {
            if (this.start.distance(p) <= minDistance) {
                closestPoint = p;
                minDistance = this.start.distance(p);
            }
        }
        return closestPoint;
    }
}