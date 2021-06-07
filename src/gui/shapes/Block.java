package gui.shapes; /**
 * @author Reut Dayan
 * @version 27.4.21
 * Gui.Shapes.Block class- implements gui.Collidable and gui.Sprite.
 */

import gui.Collidable;
import gui.Sprite;
import gui.elements.Point;
import gui.elements.Rectangle;
import biuoop.DrawSurface;
import logic.Game;
import logic.physics.Velocity;
import logic.rules.HitListener;
import logic.rules.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Gui.Shapes.Block is a class implementing gui.Collidable, gui.Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param rectangle - the rectangle field of the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * getCollisionRectangle.
     *
     * @return - the rectangle field of this block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit.
     * <p>
     * if the ball hits a vertical edge of the block, the horizontal direction should change,
     * and if the ball hits an horizontal edge of the block, the vertical direction should change.
     *
     * @param hitter          -the ball that hit the block.
     * @param collisionPoint  - the collision point- that determine the new velocity
     * @param currentVelocity - the current velocity of the object
     * @return new velocity. distinguish between vertical or horizontal collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * addToGame.
     * add this block to the game in the parameter.
     *
     * @param game - the game this block will add to.
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * getter- getRectangle.
     *
     * @return - the rectangle field.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * drawOn.
     *
     * @param d - the surface the block will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //draw the rectangle of the block
        this.rectangle.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * timePassed.
     * <p>
     * at this point- do nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * removeFromGame.
     *
     * @param game - remove the block from sprite and collidable collections.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * addHitListener.
     *
     * @param hl - add h1 to the listeners list.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removeHitListener.
     *
     * @param hl - remove h1 from the listeners list.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifyHit.
     *
     * @param hitter - which ball hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * getter-getHitListeners.
     *
     * @return the hit listeners list field.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * setter-setHitListeners.
     *
     * @param hitListeners1 -set the list to this.
     */
    public void setHitListeners(List<HitListener> hitListeners1) {
        this.hitListeners = hitListeners1;
    }
}
