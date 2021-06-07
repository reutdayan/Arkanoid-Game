package logic;

/**
 * @author Reut Dayan
 * @version 6.6.21
 * logic.Counter class.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     *
     * @param counter - the value of counter field.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * increase.
     *
     * @param number -add number to current count.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * decrease.
     *
     * @param number -subtract number from current count.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * getValue.
     *
     * @return - the current count.
     */
    public int getValue() {
        return counter;
    }

    /**
     * getValue.
     *
     * @return - the counter as a string.
     */
    public String toString() {
        return String.valueOf(this.counter);
    }
}