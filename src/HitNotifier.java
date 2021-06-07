/**
 * @author Reut Dayan
 * @version 27.4.21
 * HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * addHitListener.
     * Add hl as a listener to hit events.
     * @param hl - add hl to the hit listeners list.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener.
     * @param hl -Remove hl from the list of listeners to hit events.
     */
    void removeHitListener(HitListener hl);

}
