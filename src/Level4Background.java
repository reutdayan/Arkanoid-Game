import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level4Background class. background of the 4'th level.
 */
public class Level4Background extends Background {
    /**
     * drawOn.
     * draw the sprite to the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(221, 210, 238));
        d.fillRectangle(0, 30, 800, 600);

        d.setColor(new Color(11, 29, 56));
        for (int i = 80; i < 170; i = i + 15) {
            d.drawLine(i + 20, 400, i, 600);
        }

        d.setColor(new Color(11, 29, 56));
        d.fillCircle(100, 400, 20);

        d.setColor(new Color(13, 42, 80));
        d.fillCircle(125, 390, 25);

        d.setColor(new Color(15, 35, 69));
        d.fillCircle(165, 410, 25);

        d.setColor(new Color(19, 38, 71));
        d.fillCircle(130, 415, 23);

        d.setColor(new Color(10, 35, 69));
        d.fillCircle(155, 390, 20);


        for (int i = 605; i < 700; i = i + 15) {
            d.drawLine(i + 20, 300, i, 600);
        }

        d.setColor(new Color(11, 29, 56));
        d.fillCircle(700, 300, 20);

        d.setColor(new Color(13, 42, 80));
        d.fillCircle(675, 290, 25);

        d.setColor(new Color(15, 35, 69));
        d.fillCircle(635, 310, 25);

        d.setColor(new Color(19, 38, 71));
        d.fillCircle(670, 315, 23);

        d.setColor(new Color(10, 35, 69));
        d.fillCircle(645, 290, 20);
    }
}
