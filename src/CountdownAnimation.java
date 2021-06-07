import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

public class CountdownAnimation implements Animation {

    private int countFrom;
    private double numOfSeconds;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;

    public CountdownAnimation(int countFrom, double numOfSeconds, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();

    }


    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(new Color(204, 229, 255));
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);

        d.setColor(Color.BLACK);
        this.gameScreen.getSprites().get(0).drawOn(d);
        this.gameScreen.drawAllOn(d);
        //this.gui.show(d);
        if (this.countFrom==0){
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO", 32);
        }
        else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 32);
        }

        if (countFrom<3) {
            this.sleeper.sleepFor((long)numOfSeconds*1000/3);
        }
        this.countFrom--;

    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom<-1){
            return true;
        }
        return false;
    }
}
