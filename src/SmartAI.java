import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <b>Name: </b>SmartAI.java
 * <br>
 * <p>
 * AI entity with bfs pathfinding.
 * </p>
 * <br><b>Created:</b> 17/11/2019
 * <br><b>Last Modified:</b> 22/11/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 0.1
 * <br>
 * <br><b>History:</b>
 * <br>
 * <ul>
 * 	<li>Version 0.1 (17/11/2019)</li>
 * </ul>
 */

public class SmartAI extends Entity {

    private Manager gm;

    /**
     * create a new instance of a Smart AI.
     *
     * @param filePath file path of the object sprite.
     * @param x        x coordinate of the entity.
     * @param y        y coordinate of the entity.
     */

    public SmartAI(String filePath, int x, int y, Manager gm) {

        super(x, y);
        this.filePath = "sprites/smartAI.png";
        this.gm = gm;
    }

    /**
     * move the AI towards the player.
     *
     * @param type move type of the entity.
     */

    @Override
    public void move(MoveType type) {

        //TODO implement A* pathfinding

    }

    private List<Tile> getValidNeighbours(Tile currTile) {

        List<Tile> neighbours = new ArrayList<>();
        //int currX = currTile.getxPos;
        //int currY = currTile.getyPos;

		/*

		if ((gm.getMap.getTile(x, y+1) instanceOf Floor)) {

		}

		 */

        return neighbours;
    }

    /**
     * create a string containing entity information.
     *
     * @return SmartAI as string.
     */

    @Override
    public String toString() {

        return String.format("Smart AI Entity is at %d, %d", this.getxPos(), this.getyPos());
    }
}
