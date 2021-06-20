import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level2 class. second level.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<>();
        for (int i = 1; i <= this.numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(260 - 14.5 * i, -4));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Level2Background();
    }

    /**
     * getColors.
     *
     * @return -list of colors.
     */
    private ArrayList<Color> getColors() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);
        colors.add(Color.CYAN);
        return colors;
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Color> colors = this.getColors();
        int startX = 10;
        int y = 200;
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Block b = new Block(new Rectangle(new Point(startX, y), 780 / this.numberOfBlocksToRemove(),
                    30, colors.get(i)));
            startX = startX + 780 / this.numberOfBlocksToRemove();
            blocks.add(b);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}

