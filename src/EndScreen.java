import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean win;
    private Counter score;
    private boolean stop;

    public EndScreen(KeyboardSensor k, Counter score, boolean win) {
        this.keyboard = k;
        this.score = score;
        this.win = win;
        this.stop = false;
    }

    public void doOneFrame(DrawSurface d) {
        //the player wan
        if (this.win) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.toString(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.toString(), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    public boolean shouldStop() {

        return this.stop;
    }
}
