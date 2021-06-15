/**
 * @author Reut Dayan
 * @version 27.4.21
 * Gui.Shapes.Paddle class- implementing Sprite and Collidable
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * Gui.Shapes.Paddle class- implementing Sprite and Collidable.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int speed;

    /**
     * Constructor.
     *
     * @param keyboard  - set the keyboard field.
     * @param rectangle - set the rectangle field.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.speed = speed;
    }

    /**
     * moveLeft.
     * if the paddle exit the left screen - stop to move left
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > 10) {
            this.moveOneStepX(-speed);
        }
    }

    /**
     * moveRight.
     * if the paddle exit the right screen - stop to move right
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + rectangle.getWidth() + 10 < GameLevel.WIDTH) {
            this.moveOneStepX(speed);
        }

    }

    // Sprite

    /**
     * timePassed.
     * if LEFT_KEY press - move to left, if RIGHT_KEY press move to right
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn.
     * draw the paddle on the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    // Collidable

    /**
     * getCollisionRectangle.
     *
     * @return - the rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * getAngleByRegion.
     * calc the region the collision point (1-5)
     *
     * @param collisionPoint - the point on the paddle
     * @return - the angle according to the region.
     */
    private int getAngleByRegion(Point collisionPoint) {

        //width of each region on the paddle
        double width = this.rectangle.getWidth() / 5;

        //start point shift to the place of the paddle
        double start = collisionPoint.getX() - this.rectangle.getUpperLeft().getX();
        int region = (int) Math.ceil(start / width);

        //return the angle accordion to the region
        if (region == 1) {
            return 300;
        } else if (region == 2) {
            return 330;
        } else if (region == 3) {
            return 360;
        } else if (region == 4) {
            return 30;
        }

        //region 5.
        return 60;
    }

    /**
     * getSpeed.
     *
     * @param v - current velocity (dx, dy)
     * @return - calc the speed to fit methode fromAngleAndSpeed
     */
    private double getSpeed(Velocity v) {
        return Math.sqrt(v.getDx() * v.getDx() + v.getDy() * v.getDy());
    }

    /**
     * hit.
     *
     * @param hitter          - the hitting ball.
     * @param collisionPoint  - the collision point- that determine the new velocity
     * @param currentVelocity - the current velocity of the object
     * @return - the new velocity (after the collision).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //the ball hit the top line of the paddle
        if (collisionPoint.isInLine(rectangle.getTopHorizontalLLine())) {
            int angle = getAngleByRegion(collisionPoint);
            double speed = getSpeed(currentVelocity);
            return Velocity.fromAngleAndSpeed(angle, speed);
        } else { //the ball hit the side or the bottom of the paddle
            Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

            // check if the collision point is on horizontal line in the rectangle
            if (collisionPoint.isInLine(rectangle.getTopHorizontalLLine())
                    || collisionPoint.isInLine(rectangle.getBottomHorizontalLLine())) {

                //set the y value of the velocity
                newVelocity.setDy(-newVelocity.getDy());
            }

            // check if the collision point is on vertical line in the rectangle
            if (collisionPoint.isInLine(rectangle.getLeftVerticalLine())
                    || collisionPoint.isInLine(rectangle.getRightVerticalLine())) {

                // set the x value of the velocity
                newVelocity.setDx(-newVelocity.getDx());
            }
            return newVelocity;
        }

    }

    /**
     * addToGame.
     * Add this paddle to the game.
     *
     * @param game - the game this block will add to.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * moveOneStepX.
     * move the paddle one step according to dx.
     *
     * @param dx - the change in x axis.
     */
    public void moveOneStepX(double dx) {
        this.rectangle.getUpperLeft().setX(this.rectangle.getUpperLeft().getX() + dx);
    }
}