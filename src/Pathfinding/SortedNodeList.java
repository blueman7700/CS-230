package Pathfinding;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <b>Name: </b>SortedNodeList.java
 * <br>
 * <p>
 * Sorted list containing pathfinding nodes.
 * </p>
 * <br><b>Created:</b> 05/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public class SortedNodeList {

	private ArrayList<Node> list = new ArrayList<>();

	/**
	 * get the first element from the list.
	 *
	 * @return The first element from the list.
	 */
	public Node getFirst() {
		return list.get(0);
	}

	/**
	 * Empty the list.
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * Add an element to the list - causes sorting.
	 *
	 * @param n The Node to add to the list.
	 */
	public void add(Node n) {
		list.add(n);
		Collections.sort(list);
	}

	/**
	 * Remove an element from the list.
	 *
	 * @param n The Node to remove from the list.
	 */
	public void remove(Node n) {
		list.remove(n);
	}

	/**
	 * Get the number of elements in the list.
	 *
	 * @return The number of element in the list.
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Check if an element is in the list
	 *
	 * @param n The Node to search for
	 * @return True if the element is in the list
	 */
	public boolean contains(Node n) {
		return list.contains(n);
	}
}
