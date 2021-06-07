package gui;

import gui.elements.Point;

/**
 * @author Reut Dayan
 * @version 27.4.21
 * gui.CollisionInfo class- the information of the collision
 * (the point of the collision and the collidable object)
 */

public class CollisionInfo {

    private Collidable collidable;
    private Point point;

    /**
     * Constructor.
     *
     * @param collidable - th collidable object
     * @param point      - the point of the collision.
     */
    public CollisionInfo(Collidable collidable, Point point) {
        this.collidable = collidable;
        this.point = point;
    }

    /**
     * collisionPoint.
     *
     * @return -the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return point;
    }

    /**
     * collisionObject.
     *
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidable;
    }
}
