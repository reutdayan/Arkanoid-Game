package gui; /**
 * @author Reut Dayan
 * @version 30.3.21
 * gui.SpriteCollection class- collection of all the sprites in the game.
 */

import biuoop.DrawSurface;
import gui.Sprite;

import java.util.ArrayList;

/**
 * gui.SpriteCollection class- collection of all the sprites in the game.
 */
public class SpriteCollection {

    private ArrayList<Sprite> sprites;

    /**
     * Constructor.
     *
     * @param sprites - an array list of sprites.
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Constructor.
     * <p>
     * create an empty array for sprites, and set it as field of gui.SpriteCollection
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * addSprite.
     *
     * @param s - add sprite to the array of sprites.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * notifyAllTimePassed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> notifySprites = new ArrayList<>(this.sprites);
        for (Sprite s : notifySprites) {
            s.timePassed();
        }
    }

    /**
     * drawAllOn.
     * call drawOn(d) on all sprites.
     *
     * @param d - the surface the sprites will draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * getSprites.
     *
     * @return - the array of the sprites (field od SpritesCollection)
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}