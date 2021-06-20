import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * AnimationRunner class. run the animation on the screen.
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param gui             -gui.
     * @param framesPerSecond - how many frames per second.
     * @param sleeper         - sleeper element.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * constructor.
     *
     * @param gui -gui.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * run.
     * @param animation - run the animation on the screen.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}