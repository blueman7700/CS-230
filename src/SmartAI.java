import Pathfinding.Path;
import Pathfinding.Step;

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
    private static String FILE_PATH = "sprites/smartAI.png";
    private AStar pathfinder;

    /**
     * create a new instance of a Smart AI.
     *
     * @param x        x coordinate of the entity.
     * @param y        y coordinate of the entity.
     * @param gm       game manager
     */

    public SmartAI(int x, int y, Manager gm) {

        this.xPos = x;
        this.yPos = y;
        this.gm = gm;

        pathfinder = new AStar(gm.getMap());
    }

    /**
     * move the AI towards the player.
     *
     * @param type move type of the entity.
     */

    @Override
    public void move(MoveType type) {

        //calculate the path to the player
        Path pathToPlayer = pathfinder.getPath(getxPos(), getyPos(), gm.getPlayer().getxPos(), gm.getPlayer().getyPos());

        if (pathToPlayer == null) {
            this.randMove();
        }else {
            //move the entity closer to the player
            Step nextMove = pathToPlayer.getStep();
            this.setPosition(nextMove.getX(), nextMove.getY());
        }
    }

    /**
     * Move the AI in the first available direction. AI will check UP, DOWN, LEFT then RIGHT in that order.
     * If no valid move is available then the AI will stay where it is
     */

    private void randMove() {

        //check tile above
        if (gm.getMap().getTile(xPos, yPos - 1).getWalkable()) {
            setPosition(xPos, yPos - 1);

        //check tile below
        } else if (gm.getMap().getTile(xPos, yPos + 1).getWalkable()) {
            setPosition(xPos, yPos + 1);

        //check tile to the left
        } else if (gm.getMap().getTile(xPos - 1, yPos).getWalkable()) {
            setPosition(xPos - 1, yPos);

        //check tile to the right
        } else if (gm.getMap().getTile(xPos + 1, yPos).getWalkable()) {
            setPosition(xPos + 1, yPos);

        //if no moves are possible then stay put.
        } else {
            setPosition(xPos, yPos);
        }
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

    /**
     * Get the file path of the entity sprite.
     *
     * @return file path string
     */

    public String getFilePath() {
        return FILE_PATH;
    }
}
