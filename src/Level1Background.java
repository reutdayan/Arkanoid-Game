import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level1Background class. background of the first level.
 */
public class Level1Background extends Background {

    /**
     * drawOn.
     * draw the sprite to the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 20, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 175, 60);
        d.drawCircle(400, 175, 90);
        d.drawCircle(400, 175, 120);
        d.drawLine(400, 40, 400, 310);
        d.drawLine(265, 175, 535, 175);
    }
}
