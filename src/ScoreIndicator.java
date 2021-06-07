import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * Gui.ScoreIndicator implements Sprite- save the score of the game and show it on the screen.
 */
public class ScoreIndicator implements Sprite {

    private Rectangle rec;
    private Counter score;


    /**
     * Constructor.
     *
     * @param rec   -the rectangle field.
     * @param score - the score field.
     */
    public ScoreIndicator(Rectangle rec, Counter score) {
        this.rec = rec;
        this.score = score;
    }

    /**
     * drawOn.
     * draw the sprite to the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //draw the rectangle
        d.setColor(Color.BLACK);
        d.drawText((int) this.rec.getWidth() / 2 + (int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getHeight() / 2 + (int) this.rec.getUpperLeft().getY(),
                "the score is:" + this.score.toString(), 25);
    }

    /**
     * timePassed.
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * addToGame.
     *
     * @param game - add sprite to game
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
