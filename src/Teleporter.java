/**
 * <b>Name: </b>Teleporter.java
 * <p>
 * Teleporter tile
 * </p>
 * <br><b>Created:</b> 26/11/2019
 * <br><b>Last Modified:</b> 02/12/2019
 *
 * @author Negrusa Sergiu
 * @version 1.0
 * <br><b>History:</b>
 * <li>Version 1.0 (26/11/2019)</li>
 * </ul>
 */
public class Teleporter extends Tile {
    private int xPos;
    private int yPos;
    private Teleporter partner;

    /**
     * create an instance of Teleporter.
     *
     * @param partner we need the partner in order to connect teleporters
     * @param xPos    x position of the teleporter
     * @param yPos    y position of the teleporter
     */
    public Teleporter(Teleporter partner, int xPos, int yPos){
        //TODO: add superclass constructor
        setPartner(partner);
        setPos(xPos, yPos);
    }


    /*
     * method that teleports the Player to the other teleporter
     * @param currentPlayer we need this to check if the player is on teleporter's position
     * @return the teleporter on which the player is now
     */

    /*
    Do we really need this method?

    public Teleporter teleport(Player currentPlayer){
        if(this.getxPos() == currentPlayer.getxPos() &&
                this.getyPos() == currentPlayer.getyPos()){

            currentPlayer.setxPos(currentTeleporter.getPartner().getxPos());
            currentPlayer.setyPos(currentTeleporter.getPartner().getyPos());
        }
        return this.getPartner();
    }
    */

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    /**
     * set the position of the teleporter.
     *
     * @param xPos x coordinate.
     * @param yPos y coordinate.
     */

    private void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private void setPartner(Teleporter partner) {
        this.partner = partner;
    }

    public Teleporter getPartner() {
        return partner;
    }

    /**
     * convert teleporter tile into a string
     *
     * @return teleporter as string
     */

    @Override
    public String toString() {
        return String.format("Teleporter is at %d, %d.\n Teleporter pair location: %d %d\n", getxPos(), getyPos(), getPartner().getxPos(), getPartner().getyPos());
    }
}
