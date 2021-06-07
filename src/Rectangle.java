/**
 * @author Reut Dayan
 * @version 27.4.21
 * Gui.Rectangle class- upper left point and width and height of the rectangle.
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Gui.Rectangle class- two points- rectangle in two dimensional space.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * constructor.
     * Create a new rectangle with location of upper left point and width/height
     *
     * @param upperLeft - upper left point of the rectangle
     * @param width     - the width of the rectangle
     * @param height    - the height of the rectangle
     * @param color     - the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * constructor.
     * Create a new rectangle with location of upper left point and width/height
     *
     * @param upperLeft - upper left point of the rectangle
     * @param width     - the width of the rectangle
     * @param height    - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this(upperLeft, width, height, Color.BLACK);
    }

    /**
     * accessors - getUpperLeft.
     *
     * @return - the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * accessors - getWidth.
     *
     * @return - the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * accessors - getHeight.
     *
     * @return - the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getTopHorizontalLLine.
     *
     * @return - the top horizontal line of the rectangle (y=0)
     */
    public Line getTopHorizontalLLine() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        return new Line(upperLeft, upperRight);
    }

    /**
     * getBottomHorizontalLLine.
     *
     * @return - the bottom horizontal line of the rectangle (y=height)
     */
    public Line getBottomHorizontalLLine() {
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        return new Line(lowerLeft, lowerRight);
    }

    /**
     * getLeftVerticalLine.
     *
     * @return - the left vertical line of the rectangle (x=0)
     */
    public Line getLeftVerticalLine() {
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        return new Line(upperLeft, lowerLeft);
    }

    /**
     * getRightVerticalLine.
     *
     * @return - the right vertical line of the rectangle (x=width)
     */
    public Line getRightVerticalLine() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        return new Line(upperRight, lowerRight);
    }

    /**
     * intersectionPoints.
     * the method calc all the intersection points between the line and all the lines in the rectangle.
     *
     * @param line - the line we will check the intersection point with the rectangle.
     * @return -List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<Point>();
        if (line.intersectionWith(this.getLeftVerticalLine()) != null) {
            intersectionPoints.add(line.intersectionWith(this.getLeftVerticalLine()));
        }
        if (line.intersectionWith(this.getRightVerticalLine()) != null) {
            intersectionPoints.add(line.intersectionWith(this.getRightVerticalLine()));
        }
        if (line.intersectionWith(this.getBottomHorizontalLLine()) != null) {
            intersectionPoints.add(line.intersectionWith(this.getBottomHorizontalLLine()));
        }
        if (line.intersectionWith(this.getTopHorizontalLLine()) != null) {
            intersectionPoints.add(line.intersectionWith(this.getTopHorizontalLLine()));
        }
        return intersectionPoints;
    }

    /**
     * drawOn.
     * draw the rectangle on the given DrawSurface
     *
     * @param surface - the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }
}
