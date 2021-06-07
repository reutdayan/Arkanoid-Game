package logic.rules;

import gui.shapes.Ball;
import gui.shapes.Block;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * logic.rules.HitListener interface.
 */
public interface HitListener {

    /**
     * hitEvent.
     * <p>
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit - the block being hit.
     * @param hitter   - The hitter parameter is the Gui.Shapes.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
