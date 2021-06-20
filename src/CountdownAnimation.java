import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * CountdownAnimation class. An animation scree- count from 3 to 1 before the level starts.
 */
public class CountdownAnimation implements Animation {

    private int countFrom;
    private double numOfSeconds;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param countFrom    - count from (3).
     * @param numOfSeconds - number of seconds to all count.
     * @param gameScreen   - the screen of the game.
     */
    public CountdownAnimation(int countFrom, double numOfSeconds, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();

    }


    /**
     * doOneFrame.
     *
     * @param d - draw all the elements n the screen for one frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(new Color(204, 229, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.BLACK);
        this.gameScreen.getSprites().get(0).drawOn(d);
        this.gameScreen.drawAllOn(d);
        //this.gui.show(d);
        if (this.countFrom == 0) {
            d.setColor(new Color(51, 153, 255));
            d.drawText(d.getWidth() / 2 + 2, d.getHeight() / 2, "GO", 35);
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO", 32);

        } else {
            d.setColor(new Color(51, 153, 255));
            d.drawText(d.getWidth() / 2 + 2, d.getHeight() / 2, Integer.toString(countFrom), 35);
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 32);

        }

        if (countFrom < 3) {
            this.sleeper.sleepFor((long) numOfSeconds * 1000 / 3);
        }
        this.countFrom--;

    }

    /**
     * shouldStop.
     *
     * @return - true if the count down finished false otherwise.
     */
    @Override
    public boolean shouldStop() {
        if (this.countFrom < -1) {
            return true;
        }
        return false;
    }
}
