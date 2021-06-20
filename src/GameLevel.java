/**
 * @author Reut Dayan
 * @version 27.4.21
 * Game class- the class that manage the game.
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class- the class that manage the game.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    private static final int EDGE = 10;
    private Sleeper sleeper;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter currentScore;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Constructor.
     * <p>
     * create new sprites, environment and sleeper to game.
     */


    /**
     * Constructor.
     * create new sprites, environment and sleeper to game.
     *
     * @param gui      -gui.
     * @param level    - level.
     * @param keyboard - keyboard.
     * @param runner   - animation runner.
     * @param score    - current score.
     */
    public GameLevel(GUI gui, LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.blockCounter = new Counter(this.level.numberOfBlocksToRemove());
        this.ballCounter = new Counter(this.level.numberOfBalls());
        this.currentScore = score;
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
    }

    /**
     * addCollidable.
     *
     * @param c - add collidable to collidabels array list.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite.
     *
     * @param s - add sprite s to sprites array list.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removeCollidable.
     *
     * @param c -remove c from collidable collection.
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * removeSprite.
     *
     * @param s -remove s from Sprite collection.
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }

    /**
     * createDeathRegion.
     * creates the bottom block.
     *
     * @param lisener     - the listener added to the block.
     * @param collidables - enter the block created to collidables array list.
     * @param color       - set the color of the block created.
     */
    private void createDeathRegion(HitListener lisener, ArrayList<Collidable> collidables, Color color) {
        Block b = new Block(new Rectangle(new Point(0, HEIGHT - 1), WIDTH, 1, color));
        b.addHitListener(lisener);
        collidables.add(b);
    }

    /**
     * createFrame.
     * creates the blocks of the frame.
     *
     * @param collidables - enter the block created to collidables array list.
     * @param color       - set the color of the block created.
     */
    private void createFrame(ArrayList<Collidable> collidables, Color color) {
        collidables.add(new Block(new Rectangle(new Point(0, 20), WIDTH, EDGE, color)));
        collidables.add(new Block(new Rectangle(new Point(0, 20), EDGE, HEIGHT, color)));
        collidables.add(new Block(new Rectangle(new Point(WIDTH - EDGE, 20), EDGE, HEIGHT, color)));
    }

    /**
     * createBalls.
     * create 2 balls.
     *
     * @param color      - set te color of the ball to color.
     * @param velocities - list of velocities of the balls.
     */
    private void createBalls(Color color, List<Velocity> velocities) {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 540), 5, color, this.environment);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
        }
    }

    /**
     * createPaddle.
     * create a paddle.
     *
     * @param collidables - the collidable array the paddle will add to.
     * @param keyboard1   - ghe keyboard of the paddle.
     */
    private void createPaddle(ArrayList<Collidable> collidables, biuoop.KeyboardSensor keyboard1) {
        collidables.add(new Paddle(keyboard1, new Rectangle(
                new Point((WIDTH - this.level.paddleWidth()) / 2, (HEIGHT - 25)),
                this.level.paddleWidth(), 20, Color.ORANGE), this.level.paddleSpeed()));
    }

    /**
     * createBlocks.
     * create blocks to the game.
     *
     * @param score         - the hit listener who keep track of the score.
     * @param blockListener - the hit listener eho remove the blocks when hitted.
     * @param collidables   - the collidable array the blocks will add to.
     */
    private void createBlocks(HitListener score, HitListener blockListener, ArrayList<Collidable> collidables) {
        for (Block b : this.level.blocks()) {
            b.addHitListener(blockListener);
            b.addHitListener(score);
            collidables.add(b);
        }
    }

    /**
     * initialize.
     * Initialize a new game: create the Blocks and Gui.Shapes.Ball (and Gui.Shapes.Paddle)
     * and add them to the game.
     */
    public void initialize() {

        // array list of collidable objects
        ArrayList<Collidable> collidables = new ArrayList<>();

        if (this.level.getBackground() != null) {
            this.addSprite(this.level.getBackground());
        }

        //create death region
        HitListener ballListener = new BallRemover(this, ballCounter);
        createDeathRegion(ballListener, collidables, Color.BLACK);

        // create objects in the game
        createBalls(Color.WHITE, this.level.initialBallVelocities());
        createFrame(collidables, Color.GRAY);

        //create blocks
        //create a block remover listener
        BlockRemover blockListener = new BlockRemover(this, this.blockCounter);
        //create score tracking listener
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.currentScore);
        createBlocks(scoreListener, blockListener, collidables);

        //create paddle
        createPaddle(collidables, keyboard);

        //add score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(currentScore, this.level.levelName());
        scoreIndicator.addToGame(this);

        // add to game all collidable objects.
        for (Collidable collidable : collidables) {
            collidable.addToGame(this);
        }


    }

    /**
     * run the level.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(3, 2, sprites));

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(204, 229, 255));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);

        d.setColor(Color.BLACK);
        this.sprites.getSprites().get(0).drawOn(d);
        this.sprites.drawAllOn(d);

        //this.gui.show(d);
        this.sprites.notifyAllTimePassed();

        if (blockCounter.getValue() == 0) {
            this.currentScore.increase(100);
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            //gui.close();
            //return running;
            return true;
        }
        //return !running;
        return false;

    }

    /**
     * gameOver.
     *
     * @return true if no more balls remain anf false otherwise.
     */
    public boolean gameOver() {
        if (this.ballCounter.getValue() <= 0) {
            return true;
        }
        return false;
    }
}