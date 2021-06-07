package logic.rules;

import gui.shapes.Ball;
import gui.shapes.Block;
import logic.Counter;
import logic.Game;

/**
 * @author Reut Dayan
 * @version 6.6.21
 * Gui.Shapes.Ball remover implements hit listener.
 */
public class BallRemover implements HitListener {

    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game           - in which game are we.
     * @param remainingBalls - how many balls remained.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * hitEvent -what the listener should do when a block was being hit.
     *
     * @param beingHit - the block being hit.
     * @param hitter   - the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
