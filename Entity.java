/**
 * <b>Name: </b>Entity.java
 * 	<br>
 *		<p>
 *			Entity object. This class contains abstract methods.
 *		</p>
 * 	<br><b>Created:</b> 17/11/2019
 * 	<br><b>Last Modified:</b> 22/11/2019
 * 	<br> - no copyright
 * <hr>
 * @version 0.1
 *	<br>
 * 	<br><b>History:</b>
 * 	<br>
 *		<ul>
 *			<li>Version 0.1 (17/11/2019)</li>
 * 		</ul>
 * @author Oliver Morris
 */

public abstract class Entity extends Sprite{

	private int xPos;
	private int yPos;

	/**
	 * create a new entity
	 * @param filePath path to the entity sprite
	 * @param x x coordinate
	 * @param y y coordinate
	 */

	public Entity(String filePath, int x, int y) {

		super(filePath);
		this.xPos = x;
		this.yPos = y;
	}

	/**
	 * get the current x coordinate
	 * @return x coordinate
	 */

	public int getxPos() {

		return this.xPos;
	}

	/**
	 * get the current y coordinate
	 * @return y coordinate
	 */

	public int getyPos() {

		return this.yPos;
	}

	/**
	 * set the x coordinate
	 * @param x x coordinate
	 */

	public void setxPos(int x) {

		this.xPos = x;
	}

	/**
	 * set the y coordinate
	 * @param y y coordinate
	 */

	public void setyPos(int y) {

		this.yPos = y;
	}

	/**
	 * move the entity in a specified way. This method is abstract.
	 * @param type movement type
	 */

	public abstract void move(MoveType type);
}
