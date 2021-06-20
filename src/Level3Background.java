import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level3Background class. background of the third level.
 */
public class Level3Background extends Background {
    /**
     * drawOn.
     * draw the sprite to the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(97, 210, 230));
        d.fillRectangle(0, 20, 800, 600);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(50, 400, 100, 200);
        d.setColor(new Color(230, 230, 250));
        for (int i = 60; i < 140; i = i + 18) {
            for (int j = 410; j < 590; j = j + 28) {
                d.fillRectangle(i, j, 8, 18);
            }
        }
        d.setColor(new Color(169, 169, 169));
        d.fillRectangle(90, 350, 20, 50);
        d.setColor(new Color(245, 245, 245));
        d.fillRectangle(97, 220, 6, 130);
        d.setColor(new Color(0, 0, 153));
        d.fillCircle(99, 220, 11);
        d.setColor(new Color(153, 255, 255));
        d.fillCircle(99, 220, 7);
        d.setColor(new Color(119, 136, 153));
        d.fillCircle(99, 220, 3);
    }
}
