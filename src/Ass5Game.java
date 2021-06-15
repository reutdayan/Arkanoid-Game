import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

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
//        LevelInformation level1 = new FinalFour();
//        GameLevel game = new GameLevel(level1);
//        game.initialize();
//        game.run();

//        GUI gui = new GUI("game", 800, 600);
//        AnimationRunner runner = new AnimationRunner(gui);
//        KeyboardSensor keyboard = gui.getKeyboardSensor();

        //GameFlow game = new GameFlow(runner,keyboard,gui);
        GameFlow game = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        game.runLevels(levels);
    }
}


