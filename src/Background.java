/**
 * @author Reut Dayan
 * @version 20.6.21
 * Background abstract class. set the background elements of all levels.
 */

public abstract class Background implements Sprite {

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
