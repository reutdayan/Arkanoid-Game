import biuoop.DrawSurface;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Animation interface. create an animation screen.
 */
public interface Animation {

    /**
     * doOneFrame.
     *
     * @param d - draw all the elements n the screen for one frame.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop.
     *
     * @return when the screen should stop.
     */
    boolean shouldStop();
}