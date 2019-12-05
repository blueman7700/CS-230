package Pathfinding;

/**
 * <b>Name: </b>Node.java
 * <br>
 * <p>
 * Pathfinding node.
 * </p>
 * <br><b>Created:</b> 05/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public class Node implements Comparable<Node>{

	private int cost;				//cost to move to this node
	private int heuristicCost;		//cost to move from this node to the end node
	private int depth;
	private boolean pathable;		//mark if the node can be pathed to by the AI.

	private int x;
	private int y;

	private Node parent;			//node parent

	/**
	 * Create a new node instance.
	 *
	 * @param x		  	x coordinate.
	 * @param y			y coordinate.
	 * @param pathable  can the node be moved to.
	 */

	public Node(int x, int y, boolean pathable) {

		this.x = x;
		this.y = y;
		this.pathable = pathable;
	}

	/**
	 * set the parent of the current node.
	 *
	 * @param parent new parent for the node.
	 */

	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * set the move cost for the node.
	 *
	 * @param c cost to move to the node.
	 */

	public void setCost(int c) {
		cost = c;
	}

	/**
	 * set the depth of the current node.
	 *
	 * @param d node depth.
	 */

	public void setDepth(int d) {
		depth = d;
	}

	/**
	 * set the move cost from this node to the end node.
	 *
	 * @param heuristicCost new heuristic cost.
	 */

	public void setHeuristicCost(int heuristicCost) {
		this.heuristicCost = heuristicCost;
	}

	/**
	 * get the parent of this node.
	 *
	 * @return node parent.
	 */

	public Node getParent() {
		return parent;
	}

	/**
	 * get the current depth of the node.
	 *
	 * @return node depth.
	 */

	public int getDepth() {
		return depth;
	}

	/**
	 * get the cost of moving to this node.
	 *
	 * @return node cost.
	 */

	public int getCost() {
		return cost;
	}

	/**
	 * get the move cost from this node to the end node.
	 *
	 * @return heuristic cost.
	 */

	public int getHeuristicCost() {
		return heuristicCost;
	}

	/**
	 * get the x coordinate of the node.
	 *
	 * @return x coordinate.
	 */

	public int getX() {
		return x;
	}

	/**
	 * get the y coordinate of the node.
	 *
	 * @return y coordinate.
	 */

	public int getY() {
		return y;
	}

	/**
	 * test equality between this and another object.
	 *
	 * @param obj object to test.
	 * @return false if obj is not a node, otherwise returns true if equal.
	 */

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}else {
			return this == obj;
		}
	}

	/**
	 * get the hash code for this node.
	 *
	 * @return node hash code.
	 */

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}

	/**
	 * compare two nodes.
	 *
	 * @param n node to compare.
	 * @return -1 if param node is greater than current node, 1 if less than, 0 if equal.
	 */

	@Override
	public int compareTo(Node n) {

		int thisTotCost = this.heuristicCost + this.cost;
		int nodeTotCost = n.getHeuristicCost() + n.getCost();

		if (thisTotCost  > nodeTotCost) {
			return 1;
		}else if (thisTotCost < nodeTotCost){
			return -1;
		}else {
			return 0;
		}
	}

	/**
	 * convert node to string.
	 *
	 * @return node as string.
	 */

	@Override
	public String toString() {

		return String.format("Node at %d,%d. pathable: %s.", x, y, pathable);
	}

}
