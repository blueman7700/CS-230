/**
 * <b>Name: </b>LineAI.java
 * <br>
 * <p>
 * AI entity that moves in a straight line.
 * </p>
 * <br><b>Created:</b> 29/11/2019
 * <br><b>Last Modified:</b> 02/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1
 */

public class LineAI extends Entity {

    private Manager gm;
    private MoveType direction;
    private int loopCount = 0;
    private static String FILE_PATH = "sprites/lineAI.png";

    /**
     * create a new instance of a Line AI.
     *
     * @param x         x coordinate of the entity.
     * @param y         y coordinate of the entity.
     * @param direction direction the ai will move.
     * @param gm the game Manager.
     */
    public LineAI(int x, int y, Manager gm, MoveType direction) {

        this.xPos = x;
        this.yPos = y;
        this.gm = gm;
        this.direction = direction;
    }

    /**
     * move the AI in a straight line.
     *
     * @param type move type of the entity.
     */
    @Override
    public void move(MoveType type) {

        int newX;
        int newY;

        if (type != MoveType.AUTO) {
            System.out.println("Entity move type must be set to AUTO!");
        } else {
            //get new position
            switch (direction) {
                case UP:
                    newX = this.getxPos();
                    newY = this.getyPos() - 1;
                    break;
                case DOWN:
                    newX = this.getxPos();
                    newY = this.getyPos() + 1;
                    break;
                case LEFT:
                    newX = this.getxPos() - 1;
                    newY = this.getyPos();
                    break;
                case RIGHT:
                    newX = this.getxPos() + 1;
                    newY = this.getyPos();
                    break;
                default:
                    //if the direction is not set correctly then stay at current position
                    newX = this.getxPos();
                    newY = this.getyPos();
            }

            boolean canMove = false;

            //get the next tile
            Tile nextTile = gm.getMap().getTile(newX, newY);

            //check if the tile is floor and has not content
            if (nextTile instanceof Floor && !nextTile.contains()) {

                loopCount = 0;
                canMove = true;
            }

            //check if the next tile can be moved to
            if (canMove) {
                this.setPosition(newX, newY);
            } else {
                //increment the loop counter by one
                loopCount++;

                //if the counter is greater than one then the entity is unable to move from its current position
                if (loopCount <= 1) {
                    direction = swapDirection();
                    move(MoveType.AUTO);
                }
            }
        }
    }
    
	/**
	 * gets the direction of the AI
	 * @return String value of the direction of the AI
	 */
    public String getDirection() {
    	switch (direction) {
        case DOWN:
            return "SOUTH";
        case UP:
            return "NORTH";
        case LEFT:
        	return "EAST";
        default:
            return "WEST";
    	}
    }
    
    /**
     * create a string containing entity information.
     *
     * @return SmartAI as string.
     */
    @Override
    public String toString() {

        return String.format("Line AI Entity is at %d, %d", this.getxPos(), this.getyPos());
    }

    /**
     * invert the direction of the entity.
     *
     * @return new direction.
     */
    private MoveType swapDirection() {

        switch (direction) {
            case DOWN:
                direction = MoveType.UP;
                break;
            case UP:
                direction = MoveType.DOWN;
                break;
            case LEFT:
                direction = MoveType.RIGHT;
                break;
            case RIGHT:
                direction = MoveType.LEFT;
                break;
            default:
        }
        return direction;
    }

    /**
     * get the file path of the sprite.
     *
     * @return file path.
     */
    public String getFilePath() {
        return FILE_PATH;
    }
}

