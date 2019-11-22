import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <b>Name: </b>SmartAI.java
 * 	<br>
 *		<p>
 *			AI entity with bfs pathfinding.
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

public class SmartAI extends Entity{

	//private GameManager gm;

	/**
	 * create a new instance of a Smart AI.
	 * @param filePath file path of the object sprite.
	 * @param x x coordinate of the entity.
	 * @param y y coordinate of the entity.
	 */

	public SmartAI(String filePath, int x, int y) {

		super(filePath, x, y);
		//this.gm = gm;
	}

	/**
	 * move the AI
	 * @param type
	 */

	@Override
	public void move(MoveType type) {

		//TODO implement BFS pathfinding

		int playerX;
		int playerY;
		//Tile currTile = gm.getMap.getTile(this.getxPos(), this.getyPos());

		Queue<Tile> toVisit = new LinkedList<Tile>();
		LinkedList<Tile> visited = new LinkedList<>();



		//toVisit.add(currTile);

		/*

		boolean found = false;

		while(found = false) {

			currTile = toVisit.peek();
			visited.add(currTile);
			toVisit.add(getValidNeighbors(currTile));

		}

		 */

		if (type != type.AUTO) {

			System.out.println("AI movement should be set to AUTO!");
		}

		//playerX = GameManager.getPlayerPosX
		//playerY = GameManager.getPlayerPosX


	}

	private List<Tile> getValidNeighbors(Tile currTile) {

		List<Tile> neighbors = new ArrayList<Tile>();
		//int currX = currTile.getxPos;
		//int currY = currTile.getyPos;

		/*

		if ((gm.getMap.getTile(x, y+1) instanceOf Floor)) {

		}

		 */

		return neighbors;
	}

	/**
	 * create a string containing entity information.
	 * @return SmartAI as string.
	 */

	@Override
	public String toString() {

		return String.format("Smart AI Entity is at %d, %d", this.getxPos(), this.getyPos());
	}
}
