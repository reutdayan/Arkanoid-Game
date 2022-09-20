import java.util.ArrayList;
import java.util.List;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * manage the game- create and initialize game, and run it.
 */

public class Ass6Game {

    /**
     * main.
     *
     * @param args - arguments to main.
     */
    public static void main(String[] args) {
        GameFlow game = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        game.runLevels(levels);
    }
}


