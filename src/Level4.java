import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * Level4 class. 4'th level.
 */
public class Level4 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<>();

        velocityList.add(Velocity.fromAngleAndSpeed(150, -4));
        velocityList.add(Velocity.fromAngleAndSpeed(180, -4));
        velocityList.add(Velocity.fromAngleAndSpeed(210, -4));

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Level4Background();
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
     * @return -list of colors.
     */
    private ArrayList<Color> getColors() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(new Color(255, 204, 229));
        colors.add(new Color(255, 204, 255));
        colors.add(new Color(229, 204, 255));
        colors.add(new Color(204, 204, 255));
        colors.add(new Color(204, 229, 255));
        colors.add(new Color(204, 255, 255));
        colors.add(new Color(204, 255, 229));
//
//        colors.add(Color.GRAY);
//        colors.add(Color.RED);
//        colors.add(Color.YELLOW);
//        colors.add(Color.GREEN);
//        colors.add(Color.WHITE);
//        colors.add(Color.PINK);
//        colors.add(Color.CYAN);
        return colors;
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Color> colors = this.getColors();

        int startY = 70;
        int startX = 10;
        int width = 52;
        int height = 25;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                blocks.add(createBlock(colors.get(i), new Point(startX, startY), width, height));
                startX = startX + width;
            }
            startX = 10;
            startY = startY + height;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
