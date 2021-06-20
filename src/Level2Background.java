import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level2Background class. background of the second level.
 */
public class Level2Background extends Background {
    /**
     * drawOn.
     * draw the sprite to the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 20, 800, 600);
        d.setColor(new Color(255, 204, 229));
        for (int i = 15; i < 700; i = i + 15) {
            d.drawLine(100, 100, i, 200);
        }
        d.fillCircle(100, 100, 60);
        d.setColor(new Color(255, 102, 178));
        d.fillCircle(100, 100, 50);
        d.setColor(new Color(204, 0, 102));
        d.fillCircle(100, 100, 40);
    }
}
