import logic.Game;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * manage the game- create and initialize game, and run it.
 */

public class Ass5Game {

    /**
     * main.
     *
     * @param args - arguments to main.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}


