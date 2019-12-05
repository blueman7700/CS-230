package Pathfinding;

import java.util.ArrayList;

/**
 * <b>Name: </b>Path.java
 * <br>
 * <p>
 * Object containing A* pathfinding results.
 * </p>
 * <br><b>Created:</b> 05/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public class Path {

	private ArrayList<Step> steps = new ArrayList<>();

	/**
	 * create a new instance of path.
	 */

	public Path() {}

	/**
	 * get the first step from the path.
	 *
	 * @return first step.
	 */

	public Step getStep() {

		return steps.get(0);
	}

	/**
	 * add a step to the path.
	 *
	 * @param x x coordinate.
	 * @param y y coordinate.
	 */

	public void addStep(int x, int y) {

		Step newStep = new Step(x,y);
		steps.add(newStep);
	}

	/**
	 * add step to the front of the path.
	 *
	 * @param x x coordinate.
	 * @param y y coordinate.
	 */

	public void prependStep(int x, int y) {

		Step newStep = new Step(x, y);
		steps.add(0, newStep);
	}
}
