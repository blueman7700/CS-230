/**
 * <b>Name: </b>DumbAI.java
 * <br>
 * <p>
 * AI entity with basic pathfinding.
 * </p>
 * <br><b>Created:</b> 20/11/2019
 * <br><b>Last Modified:</b> 05/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1.0
 */

public class DumbAI extends Entity{

    private Manager gm;
    private int playerX;
    private int playerY;

    private static String FILE_PATH = "sprites/dumbAI.png";

    /**
     * Create a new instance of Dumb AI.
     *
     * @param x     x coordinate.
     * @param y     y coordinate.
     * @param gm    Game Manager.
     */

    public DumbAI (int x, int y, Manager gm) {

        this.xPos = x;
        this.yPos = y;
        this.gm = gm;

        playerX = gm.getPlayer().getxPos();
        playerY = gm.getPlayer().getyPos();
    }

    /**
     * Move the AI closer to the player.
     *
     * @param type movement type.
     */

    public void move(MoveType type) {

        if (type != MoveType.AUTO) {
            System.out.println("Move Type Set Incorrectly! Set To AUTO");
        }else {

            //get the difference in the x and y coordinates between the player and entity
            int diffX = xPos - playerX;
            int diffY = yPos - playerY;
            int newX = xPos;
            int newY = yPos;

            //check difference in the X axis
            if(diffX > 0) {

                //move left
                newX--;
            }else if (diffX < 0) {

                //move right
                newX++;
            }

            //check difference in the Y axis
            if (diffY > 0) {

                //move up
                newY--;
            }else if (newY < 0) {

                //move down
                newY++;
            }

            //if the AI is further away in the X axis it will move left/right or vice-versa.
            if (Math.abs(newX) > Math.abs(newY)) {

                //if the AI can move along the x axis it will, otherwise it will try the y axis
                if (gm.getMap().getTile(newX, yPos).getWalkable()) {
                    setPosition(newX, yPos);
                }else if (gm.getMap().getTile(xPos, newY).getWalkable() && Math.abs(newY) > 0) {
                    setPosition(xPos, newY);
                }
            }else {

                //if the AI can move along the y axis it will, otherwise it will try the x axis
                if (gm.getMap().getTile(xPos, newY).getWalkable()) {
                    setPosition(xPos, newY);
                }else if (gm.getMap().getTile(newX, yPos).getWalkable() && Math.abs(newX) > 0) {
                    setPosition(newX, yPos);
                }
            }
        }
    }

    /**
     * convert AI into string.
     *
     * @return AI as string.
     */

    @Override
    public String toString() {

        return String.format("Dumb AI Entity is at %d, %d", this.getxPos(), this.getyPos());
    }

    /**
     * get the file path for the sprite.
     *
     * @return file path.
     */

    public String getFilePath() {
        return FILE_PATH;
    }
}
