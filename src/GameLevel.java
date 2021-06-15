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
    //TODO: public field wisth and height!!!!!!!!!!!!!!!!!!!
    private SpriteCollection sprites;
    private GameEnvironment environment;
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    private static final int EDGE = 10;
    private GUI gui;
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
    public GameLevel(GUI gui, LevelInformation level,KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
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
     * @param color - set te color of the ball to color.
     */
    private void createBalls(Color color, List<Velocity> velocities) {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 540), 5, color, this.environment);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
        }
//        // ball 1
//        Ball ball1 = new Ball(new Point(200, 260), 5, color, environment);
//        ball1.setVelocity(4, 4);
//        ball1.addToGame(this);
//        ballCounter.increase(1);
//        // ball 2
//        Ball ball2 = new Ball(new Point(400, 100), 5, color, environment);
//        ball2.setVelocity(4, 4);
//        ball2.addToGame(this);
//        ballCounter.increase(1);
//        // ball 3
//        Ball ball3 = new Ball(new Point(50, 50), 5, color, environment);
//        ball3.setVelocity(4, 4);
//        ball3.addToGame(this);
//        ballCounter.increase(1);
    }

    /**
     * createPaddle.
     * create a paddle.
     *
     * @param collidables - the collidable array the paddle will add to.
     * @param keyboard    - ghe keyboard of the paddle.
     */
    private void createPaddle(ArrayList<Collidable> collidables, biuoop.KeyboardSensor keyboard) {
        collidables.add(new Paddle(keyboard, new Rectangle(
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
//
//        // start point
//        int x = 290;
//        int y = 100;
//
//        // width and height to all blocks
//        int width = 50;
//        int height = 25;
//
//        // creates numBlocks of lines of blocks.
//        for (int i = 0; i < 6; i++) {
//
//            // create blocks in one line.
//            for (int j = i; j < 10; j++) {
//                createBlock(score, blockListener, collidables, colors.get(i), new Point(x, y), width, height);
//                x = x + width;
//                this.blockCounter.increase(1);
//            }
//
//            // stairs structure.
//            x = 290 + (i + 1) * width;
//            y = y + height;
//        }
        for (Block b : this.level.blocks()) {
            b.addHitListener(blockListener);
            b.addHitListener(score);
            collidables.add(b);
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

    /**
     * createBlock.
     * create one bock.
     *
     * @param score         - the hit listener who keep track of the score.
     * @param blockListener - the hit listener eho remove the blocks when hitted.
     * @param collidables   -the collidable array the block will add to.
     * @param color         - the color the block will draw in.
     * @param start         - the start point of the block.
     * @param width         - the width of the block.
     * @param height        - the height of the block.
     */
    private void createBlock(HitListener score, HitListener blockListener, ArrayList<Collidable> collidables,
                             Color color, Point start, int width, int height) {
        Block b = new Block(new Rectangle(start, width, height, color));
        b.addHitListener(blockListener);
        b.addHitListener(score);
        collidables.add(b);
    }

    /**
     * createColorList.
     * create a list of 6 colors.
     *
     * @return - a list of colors.
     */
    private ArrayList<Color> createColorList() {
        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(new Color(0, 76, 153));
        colors.add(new Color(0, 102, 204));
        colors.add(new Color(0, 128, 255));
        colors.add(new Color(51, 153, 255));
        colors.add(new Color(102, 178, 255));
        colors.add(new Color(153, 204, 255));
        colors.add(new Color(153, 204, 255));
        return colors;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    /**
     * initialize.
     * Initialize a new game: create the Blocks and Gui.Shapes.Ball (and Gui.Shapes.Paddle)
     * and add them to the game.
     */
    public void initialize() {

        // array list of collidable objects
        ArrayList<Collidable> collidables = new ArrayList<>();

        //create death region
        HitListener ballListener = new BallRemover(this, ballCounter);
        createDeathRegion(ballListener, collidables, Color.BLACK);

        // create objects in the game
        createBalls(Color.BLACK, this.level.initialBallVelocities());
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
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(0, 0), 600, 70),
                currentScore);
        scoreIndicator.addToGame(this);

        // add to game all collidable objects.
        for (Collidable collidable : collidables) {
            collidable.addToGame(this);
        }

    }

//    /**
//     * run.
//     * Run the game -- start the animation loop.
//     */
//    public void run() {
//
//        int framesPerSecond = 60;
//        int millisecondsPerFrame = 1000 / framesPerSecond;
//        while (true) {
//            long startTime = System.currentTimeMillis(); // timing
//
//            DrawSurface d = this.gui.getDrawSurface();
//            d.setColor(new Color(204, 229, 255));
//            d.fillRectangle(0, 0, WIDTH, HEIGHT);
//
//            d.setColor(Color.BLACK);
//            this.sprites.getSprites().get(0).drawOn(d);
//            this.sprites.drawAllOn(d);
//            this.gui.show(d);
//            this.sprites.notifyAllTimePassed();
//
//            // timing
//            long usedTime = System.currentTimeMillis() - startTime;
//            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//            if (milliSecondLeftToSleep > 0) {
//                this.sleeper.sleepFor(milliSecondLeftToSleep);
//            }
//            // exit (return) when there are either no more blocks or no more balls
//            if (ballCounter.getValue() == 0) {
//                return;
//            }
//            if (blockCounter.getValue() == 0) {
//                this.currentScore.increase(100);
//                return;
//            }
//        }
//    }

    public void run() {
        //TODO- implement createBallsOnTopOfPaddle
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

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
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
    public boolean gameOver(){
        if (this.ballCounter.getValue()<=0){
            return true;
        }
        return false;
    }
}