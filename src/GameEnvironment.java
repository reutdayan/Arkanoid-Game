/**
 * @author Reut Dayan
 * @version 27.4.21
 * <p>
 * GameEnvironment class - collection of all the collidable objects in the game.
 */

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * GameEnvironment class - collection of all the collidable objects in the game.
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;

    /**
     * constructor.
     *
     * @param collidables -set the arrayList field to colliables.
     */
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * constructor.
     * <p>
     * create new array list to collidables anf=d set the collidables field.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * addCollidable.
     * add the given collidable to the environment.
     *
     * @param c -add collidabe c to the array list - collidables.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * getClosestCollision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the line we will check what is the closest collision point
     * @return - the information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable col = null;
        Point closestIntersection = null;
        double minDistance = Integer.MAX_VALUE;
        boolean occurs = false;
        for (Collidable c : collidables) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection != null) {
                if (intersection.distance(trajectory.start()) <= minDistance) {
                    closestIntersection = intersection;
                    minDistance = intersection.distance(trajectory.start());
                    col = c;
                    occurs = true;
                }
            }
        }
        if (occurs) {
            CollisionInfo info = new CollisionInfo(col, closestIntersection);
            return info;
        }
        return null;
    }

    /**
     * drawOn.
     *
     * @param surface - the surface the collidable objects will draw on.
     */
    public void drawOn(DrawSurface surface) {
        for (Collidable c : collidables) {
            c.getCollisionRectangle().drawOn(surface);
        }
    }

    /**
     * getter- getCollidables.
     *
     * @return - the collidables list.
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }
}
