import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level3 class. third level.
 */
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<>();

        velocityList.add(Velocity.fromAngleAndSpeed(225, -4));
        velocityList.add(Velocity.fromAngleAndSpeed(135, -4));

        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Level3Background();
    }

    /**
     * createBlock.
     * create one bock.
     *
     * @param color  - the color the block will draw in.
     * @param start  - the start point of the block.
     * @param width  - the width of the block.
     * @param height - the height of the block.
     * @return a block.
     */
    private Block createBlock(Color color, Point start, int width, int height) {
        Block b = new Block(new Rectangle(start, width, height, color));
        return b;
    }

    /**
     * getColors.
     *
     * @return list of colors.
     */
    private ArrayList<Color> getColors() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);
        return colors;
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Color> colors = this.getColors();
        // start point
        int x = 290;
        int y = 100;

        // width and height to all blocks
        int width = 50;
        int height = 25;

        // creates numBlocks of lines of blocks.
        for (int i = 0; i < 5; i++) {

            // create blocks in one line.
            for (int j = i; j < 10; j++) {
                blocks.add(createBlock(colors.get(i), new Point(x, y), width, height));
                x = x + width;
            }
            // stairs structure.
            x = 290 + (i + 1) * width;
            y = y + height;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
