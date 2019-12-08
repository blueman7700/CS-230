import Pathfinding.Path;
import Pathfinding.Step;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * <b>Name: </b>SmartAI.java
 * <br>
 * <p>
 * AI entity with A* pathfinding.
 * </p>
 * <br><b>Created:</b> 17/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
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

        boolean canMove = false;
        int newX = xPos;
        int newY = yPos;

        //randomise the order in which the AI will check the surrounding tiles.
        Integer[] possibleMove = {0,1,2,3};
        List<Integer> tmp = Arrays.asList(possibleMove);
        Collections.shuffle(tmp);
        tmp.toArray(possibleMove);

        //loop through move array untill a move can be made
        for (int i : possibleMove) {
            if (i == 0) {
                if (gm.getMap().getTile(xPos, yPos - 1) instanceof Floor
                        && gm.getMap().getTile(xPos, yPos - 1).getWalkable()) {
                    newX = xPos;
                    newY = yPos - 1;
                    break;
                }
            }else if (i == 1) {
                if (gm.getMap().getTile(xPos, yPos + 1) instanceof Floor
                        && gm.getMap().getTile(xPos, yPos + 1).getWalkable()) {
                    newX = xPos;
                    newY = yPos + 1;
                    break;
                }
            }else if (i == 2) {
                if (gm.getMap().getTile(xPos - 1, yPos) instanceof Floor
                        && gm.getMap().getTile(xPos - 1, yPos).getWalkable()) {
                    newX = xPos - 1;
                    newY = yPos;
                    break;
                }
            }else {
                if (gm.getMap().getTile(xPos + 1, yPos) instanceof Floor
                        && gm.getMap().getTile(xPos + 1, yPos).getWalkable()) {
                    newX = xPos + 1;
                    newY = yPos;
                    break;
                }
            }
        }
        this.setPosition(newX, newY);
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
