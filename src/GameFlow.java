import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * GameFlow class. runs all the levels.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;

    /**
     * Constructor.
     *
     * @param ar  - the animation runner.
     * @param ks  - the keyboard sensor.
     * @param gui - the gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter(0);
        this.gui = gui;
    }

    /**
     * Constructor.
     */
    public GameFlow() {
        this.gui = new GUI("game", 800, 600);
        this.ar = new AnimationRunner(gui);
        this.ks = gui.getKeyboardSensor();
        this.score = new Counter(0);
    }

    /**
     * runLevels.
     *
     * @param levels - list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(gui, levelInfo, this.ks, this.ar, this.score);
            //GameLevel level = new GameLevel(levelInfo, this.score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.gameOver()) {
                EndScreen end = new EndScreen(this.ks, this.score, false);
                this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, end));
                gui.close();
            }
        }
        EndScreen end = new EndScreen(this.ks, this.score, true);
        this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, end));
        gui.close();
    }
}