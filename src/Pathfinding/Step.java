package Pathfinding;

/**
 * <b>Name: </b>Step.java
 * <br>
 * <p>
 * a pathfinding step containing coordinated.
 * </p>
 * <br><b>Created:</b> 05/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public class Step {

	private int x;
	private int y;

	/**
	 *
	 * @param x x coordinate
	 * @param y y coordinate
	 */

	public Step(int x, int y) {

		this.x = x;
		this.y = y;
	}

	/**
	 * get the x coordinate.
	 *
	 * @return x coordinate.
	 */

	public int getX() {
		return x;
	}

	/**
	 * get the y coordinate.
	 * @return y coordinate.
	 */

	public int getY() {
		return y;
	}
}
