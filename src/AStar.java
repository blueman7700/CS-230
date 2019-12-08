import Pathfinding.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Oliver Morris
 * @version 1.1
 */

public class AStar implements Pathfinder {

	private ArrayList<Node> closed = new ArrayList<>();
	private SortedNodeList open = new SortedNodeList();
	private Node[][] nodes;

	private ManhattenDistance heuristic = new ManhattenDistance();

	private static boolean ALLOW_DIAG_MOVE = false; // if diagonal movement is allowed the set to TRUE

	private Map gameMap;
	private int maxDistance;

	/**
	 * create a new instance of the A* pathfinder.
	 *
	 * @param gameMap map of the current level
	 */

	public AStar(Map gameMap) {

		this.gameMap = gameMap;
		maxDistance = 40;

		nodes = new Node[gameMap.getWidth()][gameMap.getHeight()];

		//add all map tiles to node array
		for (int i = 0; i < gameMap.getWidth(); i++) {
			for (int k = 0; k < gameMap.getHeight(); k++) {
				nodes[i][k] = new Node(i, k, (gameMap.getTile(i,k) instanceof Floor
						&& !(gameMap.getTile(i,k).contains())));
			}
		}
	}

	/**
	 * calculate the path to the player.
	 *
	 * @param sX start x coordinate.
	 * @param sY start y coordinate.
	 * @param fX target x coordinate.
	 * @param fY target y coordinate.
	 * @return path to player.
	 */

	public Path getPath(int sX, int sY, int fX, int fY) {

		if (!(gameMap.getTile(fX, fY).getWalkable())) {
			return null;
		}

		//set initial cost and depth to 0 and parent to null for starting node.
		nodes[sX][sY].setCost(0);
		nodes[sX][sY].setDepth(0);
		nodes[sX][sY].setParent(null);

		//clear lists and add start node to open array
		closed.clear();
		open.clear();
		open.add(nodes[sX][sY]);

		int maxDepth = 0;
		while ((maxDepth < maxDistance) && (open.size() != 0)) {

			//add current node to closed list and remove from queue
			Node current = open.getFirst();
			if (current == nodes[fX][fY]) {
				break;
			}

			open.removeHead();
			closed.add(current);

			//search through all the neighbours of the current node
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {

					//not a neighbour if we are at the current tile
					if ((x == 0) && (y == 0)) {
						continue;
					}

					//if diagonal movement is not allowed then either x or y must be set but not both
					if (!ALLOW_DIAG_MOVE) {
						if ((x != 0) && (y != 0)) {
							continue;
						}
					}

					//get the location of the neighbour and evaluate it
					int xp = x + current.getX();
					int yp = y + current.getY();

					//check if the new location can be moved to
					if (isValidLocation(sX, sY, xp, yp)) {

						// the cost to get to the new node is the cost to get to the current node + 1
						int nextStepCost = current.getCost() + 1;
						Node neighbour = nodes[xp][yp];

						//if the new cost we have determined is lower than the cost of a neighbour
						// then it can be re-evaluated
						if (nextStepCost < neighbour.getCost()) {
							if (open.contains(neighbour)){
								open.remove(neighbour);
							}
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}

						//if the current node hasn't been processed already then set its cost and parent accordingly
						// before adding to the open list
						if (!(open.contains(neighbour)) && !(closed.contains(neighbour))) {
							neighbour.setCost(nextStepCost);
							neighbour.setHeuristicCost(heuristic.getCost(xp, yp, fX, fY));
							neighbour.setParent(current);
							neighbour.setDepth(current.getDepth() + 1);
							maxDepth = Math.max(maxDepth, neighbour.getDepth());
							open.add(neighbour);
						}
					}
				}
			}
		}

		//if the parent of the tile at the final coordinates is null then there is no path
		if (nodes[fX][fY].getParent() == null) {
			return null;
		}

		//create the path to the player
		Path path = new Path();
		Node trgt = nodes[fX][fY];
		while (trgt != nodes[sX][sY]) {

			//add the previous step to the front of the list as we are working backwards
			path.prependStep(trgt.getX(), trgt.getY());
			trgt = trgt.getParent();
		}

		return path;
	}

	/**
	 * Check if the location is a valid move.
	 *
	 * @param sx start x coordinate.
	 * @param sy start y coordinate.
	 * @param x x coordinate of the location to check.
	 * @param y y coordinate of the location to check.
	 * @return true if the location can be moved to, else false.
	 */

	private boolean isValidLocation(int sx, int sy, int x, int y) {
		boolean invalid = (x < 0) || (y < 0) || (x >= gameMap.getWidth()) || (y >= gameMap.getHeight());

		if ((!invalid) && ((sx != x) || (sy != y))) {
			invalid = !(gameMap.getTile(x,y) instanceof Floor && !(gameMap.getTile(x, y).contains()));
		}

		return !invalid;
	}
}
