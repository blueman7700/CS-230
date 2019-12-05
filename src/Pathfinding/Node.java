package Pathfinding;

public class Node implements Comparable<Node>{

	private int cost;
	private int heuristicCost;
	private int depth;
	private boolean pathable;

	private int x;
	private int y;

	private Node parent;

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
	 *
	 * @param d
	 */

	public void setDepth(int d) {
		depth = d;
	}

	/**
	 *
	 * @param heuristicCost
	 */

	public void setHeuristicCost(int heuristicCost) {
		this.heuristicCost = heuristicCost;
	}

	/**
	 *
	 * @return
	 */

	public Node getParent() {
		return parent;
	}

	/**
	 *
	 * @return
	 */

	public int getDepth() {
		return depth;
	}

	/**
	 *
	 * @return
	 */

	public int getCost() {
		return cost;
	}

	public int getHeuristicCost() {
		return heuristicCost;
	}

	/**
	 *
	 * @return
	 */

	public int getX() {
		return x;
	}

	/**
	 *
	 * @return
	 */

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}else {
			return this == obj;
		}
	}

	@Override
	public int hashCode() {

		return heuristicCost;
	}

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

}
