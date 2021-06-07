package gui;

import gui.elements.Point;
import gui.elements.Rectangle;
import gui.shapes.Ball;
import logic.Game;
import logic.physics.Velocity;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * gui.Collidable interface - object in the game that can be collidable.
 */

public interface Collidable {

    /**
     * getCollisionRectangle.
     *
     * @return - Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          - the ball hit the collidable object.
     * @param collisionPoint  - the collision point- that determine the new velocity
     * @param currentVelocity - the current velocity of the object
     * @return new velocity. distinguish between vertical or horizontal collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * addToGame.
     * add this block to the game in the parameter.
     *
     * @param game - the game this block will add to.
     */
    void addToGame(Game game);
}

