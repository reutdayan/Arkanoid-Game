import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Reut Dayan
 * @version 20.6.21
 * KeyPressStoppableAnimation class- animation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;


    /**
     * Constructor.
     *
     * @param sensor    - keyboard sensor.
     * @param key       - string key.
     * @param animation - animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (!shouldStop()) {
            if (!this.isAlreadyPressed) {
                animation.doOneFrame(d);
            } else {
                this.isAlreadyPressed = false;
            }
        } else {
            return;
        }

    }

    @Override
    public boolean shouldStop() {
        if (this.sensor.isPressed(key)) {
            return true;
        }
        return false;
    }
}