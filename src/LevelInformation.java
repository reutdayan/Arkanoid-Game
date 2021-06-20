import java.util.List;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * LevelInformation interface. levels.
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     *
     * @return - the number of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return - list of balls velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     *
     * @return speed of the paddle.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *
     * @return - the width of the paddle.
     */
    int paddleWidth();


    /**
     * levelName.
     * the level name will be displayed at the top of the screen.
     *
     * @return - the name of the level.
     */
    String levelName();


    /**
     * getBackground.
     *
     * @return -Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * blocks.
     *
     * @return -the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();


    /**
     * numberOfBlocksToRemove.
     *
     * @return - Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}