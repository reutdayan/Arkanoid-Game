package logic.rules;

import gui.shapes.Ball;
import gui.shapes.Block;
import logic.Counter;
import logic.Game;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * a logic.rules.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          - the game we play.
     * @param removedBlocks - how many blocks has been removed.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent.
     *
     * @param beingHit - block being hit will remove from the game.
     * @param hitter   - the ball hitted the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}