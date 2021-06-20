import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level1 class. first level.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<>();
        velocityList.add(new Velocity(0, -3));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Level1Background();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(375, 150), 50, 50, Color.RED));
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
