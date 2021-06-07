/**
 * @author Reut Dayan
 * @version 27.4.21
 * ScoreTrackingListener implements HitListener.
 * change the score whenever a block being hit.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter - set the currentScore.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent.
     *
     * @param beingHit - the block being hit.
     * @param hitter   - The hitter parameter is the Gui.Shapes.Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}