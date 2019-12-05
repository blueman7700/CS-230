import Pathfinding.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class AStar implements Pathfinder {

	private ArrayList<Node> closed = new ArrayList<>();
	private SortedNodeList open = new SortedNodeList();
	private Node[][] nodes;

	private ManhattenDistance heuristic = new ManhattenDistance();

	private static boolean ALLOW_DIAG_MOVE = false;

	private Map gameMap;
	private int maxDistance = gameMap.getHeight() * gameMap.getWidth();

	public AStar(Map gameMap) {

		this.gameMap = gameMap;

		nodes = new Node[gameMap.getWidth()][gameMap.getHeight()];

		for (int i = 0; i < gameMap.getWidth(); i++) {
			for (int k = 0; k < gameMap.getHeight(); k++) {
				nodes[i][k] = new Node(i, k, gameMap.getTile(i,k).getWalkable());
			}
		}
	}

	public Path getPath(int sX, int sY, int fX, int fY) {

		if (!(gameMap.getTile(fX, fY).getWalkable())) {
			return null;
		}

		nodes[sX][sY].setCost(0);
		nodes[sX][sY].setDepth(0);
		nodes[sX][sY].setParent(null);
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
						// the cost to get to this node is cost the current plus the movement

						// cost to reach this node. Note that the heursitic value is only used

						// in the sorted open list

						int nextStepCost = current.getCost() + 1;
						Node neighbour = nodes[xp][yp];

						// if the new cost we've determined for this node is lower than

						// it has been previously makes sure the node hasn'e've
						// determined that there might have been a better path to get to

						// this node so it needs to be re-evaluated

						if (nextStepCost < neighbour.getCost()) {
							if (open.contains(neighbour)){
								open.remove(neighbour);
							}
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}

						// if the node hasn't already been processed and discarded then

						// reset it's cost to our current cost and add it as a next possible

						// step (i.e. to the open list)

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

		return null;
	}

	private boolean isValidLocation(int sx, int sy, int x, int y) {
		boolean invalid = (x < 0) || (y < 0) || (x >= gameMap.getWidth()) || (y >= gameMap.getHeight());

		if ((!invalid) && ((sx != x) || (sy != y))) {
			invalid = gameMap.getTile(x, y).getWalkable();
		}

		return !invalid;
	}
}
