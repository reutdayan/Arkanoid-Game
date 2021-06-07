package logic.physics;

import gui.elements.Point;

/**
 * @author Reut Dayan
 * @version 30.3.21
 * logic.physics.Velocity specifies the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx - the change in x-axis
     * @param dy - the change in y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed.
     *
     * @param angle - the angle
     * @param speed - the speed
     * @return - velocity (dx and dy)
     * the method convert the speed and angle to dx, dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, -dy);
    }

    /**
     * getAngle.
     *
     * @return - the angle of the velocity
     */
    public double getAngle() {
        return Math.atan(this.dy / this.dx);
    }

    /**
     * accessors getDx.
     *
     * @return the change in x-axis of this
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * accessors getDy.
     *
     * @return the change in y-axis of this
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * setDx.
     *
     * @param da - the new dx- change in x axis
     */
    public void setDx(double da) {
        this.dx = da;
    }

    /**
     * setDy.
     *
     * @param db - the new dy- change in y axis
     */
    public void setDy(double db) {
        this.dy = db;
    }

    /**
     * applyToPoint.
     *
     * @param p - the start point
     * @return - the point with the coordinate of the p plus the velocity (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
