package Pathfinding;

/**
 * <b>Name: </b>ManhattenDistance.java
 * <br>
 * <p>
 * calculates the move cost between two points.
 * </p>
 * <br><b>Created:</b> 05/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public class ManhattenDistance {

	//min cost to move from one tile to another
	private static int MIN_COST = 1;

	/**
	 * create new instance of ManhattenDistance.
	 */

	public ManhattenDistance() {}

	/**
	 * return the move cost between two points.
	 *
	 * @param sX starting x coordinate.
	 * @param sY starting y coordinate.
	 * @param fX target x coordinate.
	 * @param fY target y coordinate.
	 * @return
	 */

	public int getCost(int sX, int sY, int fX, int fY) {

		return MIN_COST * (Math.abs(sX - fX) + Math.abs(sY - fY));
	}
}
