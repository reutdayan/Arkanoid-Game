package gui.elements;

/**
 * @author Reut Dayan
 * @version 30.3.21
 * Gui.Point class- one dimensional point (x and y coordinates)
 */

public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x - the x coordinate of the point
     * @param y - the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance.
     *
     * @param other - the point from which we calculate the distance
     * @return - returns the distance between this point and other point
     */
    public double distance(Point other) {
        return Math.sqrt((((this.x - other.getX()) * (this.x - other.getX())))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * equals.
     *
     * @param other - the point we checks for
     * @return true if other point and this point are equal and false otherwise
     */
    public boolean equals(Point other) {
        return (this.x == other.getX()) && (this.y == other.getY());
    }

    /**
     * accessors getX.
     *
     * @return the x coordinate value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * accessors getY.
     *
     * @return the y coordinate value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Setter- setX.
     * <p>
     * set the x value to point.
     *
     * @param a - the new value to x.
     */
    public void setX(double a) {
        this.x = a;
    }

    /**
     * Setter- setY.
     * <p>
     * set the y value to point.
     *
     * @param b - the new value to y.
     */
    public void setY(double b) {
        this.y = b;
    }

    /**
     * isInLine.
     * check if this point is in the line.
     *
     * @param l - the line we will checl if point is on.
     * @return - true if the point is in the line, and false otherwise.
     */
    public boolean isInLine(Line l) {
        //the case where l is vertical
        if (l.isVertical()) {
            // check if the point has the same x value and if y is in between the line.
            if (this.getX() == l.start().getX() && l.isBetweenYRange(this.getY())) {
                return true;
            }
        }
        //the line is not vertical
        //calc the slope and intercept of the line
        if (this.getX() * l.calcSlope() + l.calcIntercept() == this.getY()) {
            //y is in between the line.
            if (l.isBetweenYRange(this.getY())) {
                return true;
            }
        }
        return false;
    }
}
