/**
 * <b>Name: </b>WallAI.java
 * <br>
 * <p>
 * AI entity that follows walls, prioritising walls to its left.
 * </p>
 * <br><b>Created:</b> 29/11/2019
 * <br><b>Last Modified:</b> 02/12/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 1
 */

public class WallAI extends Entity {

    private Manager gm;
    private MoveType prevDirection;
    private String filePath = "sprites/wallAI.png";
    private Tile[][] vision = new Tile[3][3]; //array containing the tiles the AI can 'see'.

    /**
     * create a new instance of a Line AI.
     *
     * @param x         x coordinate of the entity.
     * @param y         y coordinate of the entity.
     */

    public WallAI(int x, int y, Manager gm) {
        this.xPos = x;
        this.yPos = y;
        this.gm = gm;
        this.prevDirection = getStartDirection();
    }

    /**
     * move the AI along a wall, prioritising any wall to its left.
     *
     * @param type move type of the entity.
     */

    @Override
    public void move(MoveType type) {

        //set what the Entity can currently 'see'
        setVision();

        int newX = xPos;
        int newY = yPos;

        if (type != MoveType.AUTO) {
            System.out.println("Entity move type must be set to AUTO!");
        } else {
            //get new position
            //the AI will prioritise moving with a wall to its left
            switch (prevDirection) {
                //check what move to make if the previous move was upwards
                case UP:
                    if (validMove(vision[0][1])) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else if (validMove(vision[1][0])) {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;

                    }else if (validMove(vision[2][1])) {

                        newX = this.getxPos() + 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.RIGHT;

                    }else if (validMove(vision[1][2])){

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;
                    }
                    break;

                //check what move to make if the previous move was downwards
                case DOWN:

                    if (validMove(vision[2][1])) {

                        newX = this.getxPos() + 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.RIGHT;

                    }else if (validMove(vision[1][2])) {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;

                    }else if (validMove(vision[0][1])) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else if (validMove(vision[1][0])){

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;
                    }
                    break;

                //check what move to make if the previous move was to the left
                case LEFT:

                    if (validMove(vision[1][2])) {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;

                    }else if (validMove(vision[0][1])) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else if (validMove(vision[1][0])) {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;

                    }else if (validMove(vision[2][1])){

                        newX = this.getxPos() + 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.RIGHT;
                    }
                    break;

                //check what move to make if the previous move was to the right
                case RIGHT:

                    if (validMove(vision[1][0])) {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;

                    }else if (validMove(vision[1][2])) {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;

                    }else if (validMove(vision[0][1])) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else if (validMove(vision[2][1])){

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;
                    }
                    break;
                default:
                    //if the direction is not set correctly then stay at current position
                    newX = this.getxPos();
                    newY = this.getyPos();
            }

            this.setPosition(newX, newY);

        }
    }

    /**
     * set what tiles the AI can directly 'see'.
     */

    private void setVision() {

        //current x and y coordinates
        int x = this.getxPos();
        int y = this.getyPos();

        vision[0][0] = gm.getMap().getTile(x - 1, y - 1);
        vision[0][1] = gm.getMap().getTile(x - 1, y);
        vision[0][2] = gm.getMap().getTile(x - 1, y + 1);
        vision[1][0] = gm.getMap().getTile(x, y - 1);
        vision[1][1] = gm.getMap().getTile(x, y);
        vision[1][2] = gm.getMap().getTile(x, y + 1);
        vision[2][0] = gm.getMap().getTile(x + 1, y - 1);
        vision[2][1] = gm.getMap().getTile(x + 1, y);
        vision[2][2] = gm.getMap().getTile(x + 1, y + 1);
    }

    /**
     * set the direction that the ai will move initially.
     *
     * @return starting direction.
     */

    private MoveType getStartDirection() {

        setVision();

        //check if the tile to the left is walkable
        if (validMove(vision[0][1])){

            return MoveType.UP;
        }

        //check if the tile above is walkable
        if (validMove(vision[1][0])){

            return MoveType.RIGHT;
        }

        //check if the tile to the right is walkable
        if (validMove(vision[2][1])){

            return MoveType.DOWN;
        }

        //check if the tile below is walkable
        if (validMove(vision[1][2])){

            return MoveType.LEFT;
        }

        return null;
    }

    /**
     * check if a move to a tile is valid.
     *
     * @param tile tile to check
     * @return true if ai can move to the tile, else false.
     */

    private boolean validMove(Tile tile) {

        return (tile instanceof Floor && !(tile.contains()));
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

