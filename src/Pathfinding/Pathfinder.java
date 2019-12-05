package Pathfinding;

/**
 * <b>Name: </b>Pathfinder.java
 * <br>
 * <p>
 * Pathfinding interface.
 * </p>
 * <br><b>Created:</b> 05/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public interface Pathfinder {

	/**
	 * creates a path from start coordinates to final coordinates.
	 *
	 * @param startX	starting x coordinate.
	 * @param startY	starting y coordinate.
	 * @param finX 		final x coordinate.
	 * @param finY		final y coordinate.
	 * @return			path from start to finish.
	 */

	Path getPath(int startX, int startY, int finX, int finY);

}
