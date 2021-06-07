/**
 * @author Reut Dayan
 * @version 30.3.21
 * Gui.Shapes.Ball class- one point and a radius, with velocity and color.
 */

import biuoop.DrawSurface;

/**
 * Gui.Shapes.Ball class- one point and a radius, with velocity and color.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(3, 3);
    private GameEnvironment environment;

    /**
     * constructor.
     *
     * @param center - the center point of the ball (x and y coordinate)
     * @param r      -     the radius of the ball
     * @param color  - the color of the ball
     * @param env    - the game environment of the game.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment env) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.environment = env;
    }

    /**
     * constructor.
     *
     * @param x           - x coordinate of the center of the ball
     * @param y           - y coordinate of the center of the ball
     * @param r           - the radius of the ball
     * @param color       - color of the ball
     * @param environment - the game environment of the game.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment environment) {
        // call the first constructor
        this(new Point(x, y), r, color, environment);
    }

    /**
     * accessors - getX.
     *
     * @return - the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessors - getY.
     *
     * @return - the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessors - getSize.
     *
     * @return - the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * accessors - getColor.
     *
     * @return - the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn.
     *
     * @param surface - the surface the ball will draw on.
     *                the method draw the ball on the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * timePassed.
     * <p>
     * call moveOneStep methode
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * addToGame.
     *
     * @param game - tadd the ball to this game.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * setVelocity.
     *
     * @param v - the velocity og the ball
     *          the method set the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity.
     *
     * @param dx - the change in x axis
     * @param dy - the change in y axis
     *           the method set the velocity of the ball
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * accessor getVelocity.
     *
     * @return - the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * computeTrajectory.
     *
     * @return - the trajectory is the line- how the ball will move
     * without any obstacles -- its a line starting at current location, and
     * ending where the velocity will take the ball if no collisions will occur
     */
    private Line computeTrajectory() {
        return new Line(center, new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy()));
    }

    /**
     * moveOneStep.
     * move the ball in the window, if the ball reaches to the end of the window,
     * change the direction of the velocity of the ball/
     */
    public void moveOneStep() {
        //compute the trajectory
        Line trajectory = this.computeTrajectory();
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * removeFromGame.
     *
     * @param g -removing ball from this game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}