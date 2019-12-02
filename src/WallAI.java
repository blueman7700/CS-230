/**
 * <b>Name: </b>WallAI.java
 * <br>
 * <p>
 * AI entity that follows walls.
 * </p>
 * <br><b>Created:</b> 29/11/2019
 * <br><b>Last Modified:</b> 02/12/2019
 * <br> - no copyright
 * <hr>
 * @author Oliver Morris
 * @version 1
 */

public class WallAI extends Entity{

    private Manager gm;
    private MoveType direction;

    private Tile[][] vision = new Tile[3][3]; //array containing the tiles the AI can 'see'.

    /**
     * create a new instance of a Line AI.
     *
     * @param filePath file path of the object sprite.
     * @param x        x coordinate of the entity.
     * @param y        y coordinate of the entity.
     * @param direction direction the ai will move.
     */

    public WallAI(String filePath, int x, int y, Manager gm, MoveType direction) {

        super(filePath, x, y);
        this.gm = gm;
        this.direction = direction;
    }

    /**
     * move the AI in a straight line.
     *
     * @param type move type of the entity.
     */

    @Override
    public void move(MoveType type){

        //TODO: implement pathfinding for wall AI movement

        setVision();

        int newX = 0;
        int newY = 0;

        if (type != MoveType.AUTO) {
            System.out.println("Entity move type must be set to AUTO!");
        }else {
            //get new position
            switch (direction) {
                case UP:

                    break;
                case DOWN:

                    break;
                case LEFT:

                    break;
                case RIGHT:

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
            if (nextTile instanceof Floor) {
                if (((Floor)nextTile).getContent() == null) {
                    canMove = true;
                }
            }


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
     * create a string containing entity information.
     *
     * @return SmartAI as string.
     */

    @Override
    public String toString() {

        return String.format("Smart AI Entity is at %d, %d", this.getxPos(), this.getyPos());
    }
}

