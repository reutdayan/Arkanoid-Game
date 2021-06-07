/**
 * @author Reut Dayan
 * @version 27.4.21
 * <p>
 * Sprite interface - a collection of all the object that can draw on the screen of the game.
 */

import biuoop.DrawSurface;

/**
 * Sprite interface - a collection of all the object that can draw on the screen of the game.
 */
public interface Sprite {

    /**
     * drawOn.
     * draw the sprite to the screen.
     *
     * @param d - the surface of the game the sprites will draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed.
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * addToGame.
     *
     * @param game - add sprite to game
     */
    void addToGame(GameLevel game);
}