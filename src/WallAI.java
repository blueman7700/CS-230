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

    private Tile[][] vision = new Tile[3][3]; //array containing the tiles the AI can 'see'.

    /**
     * create a new instance of a Line AI.
     *
     * @param filePath  file path of the object sprite.
     * @param x         x coordinate of the entity.
     * @param y         y coordinate of the entity.
     */

    public WallAI(String filePath, int x, int y, Manager gm) {

        this.xPos = x;
        this.yPos = y;
        this.filePath = "sprites/wallAI.png";
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

        int newX;
        int newY;

        if (type != MoveType.AUTO) {
            System.out.println("Entity move type must be set to AUTO!");
        } else {
            //get new position
            //the AI will prioritise moving with a wall to its left
            switch (prevDirection) {
                //check what move to make if the previous move was upwards
                case UP:
                    if (vision[0][1].getWalkable()) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else if (vision[1][0].getWalkable()) {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;

                    }else if (vision[2][1].getWalkable()) {

                        newX = this.getxPos() + 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.RIGHT;

                    }else {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;
                    }
                    break;

                //check what move to make if the previous move was downwards
                case DOWN:

                    if (vision[2][1].getWalkable()) {

                        newX = this.getxPos() + 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.RIGHT;

                    }else if (vision[1][2].getWalkable()) {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;

                    }else if (vision[0][1].getWalkable()) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;
                    }
                    break;

                //check what move to make if the previous move was to the left
                case LEFT:

                    if (vision[1][2].getWalkable()) {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;

                    }else if (vision[0][1].getWalkable()) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else if (vision[1][0].getWalkable()) {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;

                    }else {

                        newX = this.getxPos() + 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.RIGHT;
                    }
                    break;

                //check what move to make if the previous move was to the right
                case RIGHT:

                    if (vision[1][0].getWalkable()) {

                        newX = this.getxPos();
                        newY = this.getyPos() - 1;
                        prevDirection = MoveType.UP;

                    }else if (vision[1][2].getWalkable()) {

                        newX = this.getxPos();
                        newY = this.getyPos() + 1;
                        prevDirection = MoveType.DOWN;

                    }else if (vision[0][1].getWalkable()) {

                        newX = this.getxPos() - 1;
                        newY = this.getyPos();
                        prevDirection = MoveType.LEFT;

                    }else {

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
        vision[0][1] = gm.getMap().getTile(x, y - 1);
        vision[0][2] = gm.getMap().getTile(x + 1, y - 1);
        vision[1][0] = gm.getMap().getTile(x - 1, y);
        vision[1][1] = gm.getMap().getTile(x, y);
        vision[1][2] = gm.getMap().getTile(x + 1, y);
        vision[2][0] = gm.getMap().getTile(x - 1, y + 1);
        vision[2][1] = gm.getMap().getTile(x, y + 1);
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
        if (!(vision[0][1] instanceof Floor)) {

            return MoveType.UP;
        }else if (((Floor)vision[0][1]).getContent() != null){

            return MoveType.UP;
        }

        //check if the tile above is walkable
        if (!(vision[1][0] instanceof Floor)) {

            return MoveType.RIGHT;
        }else if (((Floor)vision[1][0]).getContent() != null){

            return MoveType.RIGHT;
        }

        //check if the tile to the right is walkable
        if (!(vision[2][1] instanceof Floor)) {

            return MoveType.DOWN;
        }else if (((Floor)vision[2][1]).getContent() != null){

            return MoveType.DOWN;
        }

        //check if the tile below is walkable
        if (!(vision[1][2] instanceof Floor)) {

            return MoveType.LEFT;
        }else if (((Floor)vision[1][2]).getContent() != null){

            return MoveType.LEFT;
        }

        return null;
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

