import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * EndScreen class. An animation scree- inform the player the game has over and if he won or lose.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean win;
    private Counter score;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param k     - the keyboard sensor.
     * @param score - the current score.
     * @param win   - true if the player won and false otherwise.
     */
    public EndScreen(KeyboardSensor k, Counter score, boolean win) {
        this.keyboard = k;
        this.score = score;
        this.win = win;
        this.stop = false;
    }

    /**
     * doOneFrame.
     *
     * @param d - draw all the elements n the screen for one frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //the player wan
        if (this.win) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.toString(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.toString(), 32);
        }
    }

    /**
     * shouldStop.
     *
     * @return not this.stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
