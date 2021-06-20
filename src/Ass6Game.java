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
        for (String s : args) {
            if (s.equals("1")) {
                levels.add(new Level1());
            }
            if (s.equals("2")) {
                levels.add(new Level2());
            }
            if (s.equals("3")) {
                levels.add(new Level3());
            }
            if (s.equals("4")) {
                levels.add(new Level4());
            }
        }
        game.runLevels(levels);
    }
}


