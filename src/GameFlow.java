import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;


    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter(0);
        this.gui = gui;
    }

    public GameFlow (){
        this.gui = new GUI("game", 800, 600);
        this.ar = new AnimationRunner(gui);
        this.ks = gui.getKeyboardSensor();
        this.score = new Counter(0);
    }

    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(gui, levelInfo, this.ks, this.ar, this.score);
            //GameLevel level = new GameLevel(levelInfo, this.score);

            level.initialize();

            while (!level.shouldStop()){
                level.run();
            }

            if (level.gameOver()){
                this.ar.run(new EndScreen(this.ks,this.score,false));
                return;
            }
        }
        this.ar.run(new EndScreen(this.ks,this.score,true));
    }
}