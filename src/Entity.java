/**
 * <b>Name: </b>Entity.java
 * <br>
 * <p>
 * Entity object. This class contains abstract methods.
 * </p>
 * <br><b>Created:</b> 17/11/2019
 * <br><b>Last Modified:</b> 22/11/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 0.1
 * <br>
 * <br><b>History:</b>
 * <br>
 * <ul>
 * 	<li>Version 0.1 (17/11/2019)</li>
 * </ul>
 */

public abstract class Entity extends Sprite {

    private int xPos;
    private int yPos;

    /**
     * create a new entity
     *
     * @param x        x coordinate
     * @param y        y coordinate
     */

    public Entity(int x, int y) {

        this.xPos = x;
        this.yPos = y;
    }

    /**
     * get the current x coordinate
     *
     * @return x coordinate
     */

    public int getxPos() {

        return this.xPos;
    }

    /**
     * get the current y coordinate
     *
     * @return y coordinate
     */

    public int getyPos() {

        return this.yPos;
    }

    /**
     * set the position of the entity
     * @param x x coordinate
     * @param y y coordinate
     */

    public void setPosition(int x, int y) {

        this.xPos = x;
        this.yPos = y;
    }

    /**
     * move the entity in a specified way. This method is abstract.
     *
     * @param type movement type
     */

    public abstract void move(MoveType type);
}
