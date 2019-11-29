/**
 * <b>Name: </b>Teleporter.java
 * <p>
 * a Simple Registration Form using Java Swing
 * </p>
 * <br><b>Created:</b> 26/11/2019
 * <br><b>Last Modified:</b> 26/11/2019
 * @author Negrusa Sergiu
 * @version 1.0
 * <br><b>History:</b>
 * 	<li>Version 1.0 (26/11/2019)</li>
 * </ul>
 */
public class Teleporter{
    private int xPos;
    private int yPos;
    private Teleporter partner;

    /**
     * create an instance of Teleporter.
     * @param partner we need the partner in order to connect teleporters
     * @param xPos x position of the teleporter
     * @param yPos y position of the teleporter
     */
    public Teleporter(Teleporter partner, int xPos, int yPos){
        setPartner(partner);
        setxPos(xPos);
        setyPos(yPos);
    }

    /**
     * method that teleports the Player to the other teleporter
     * @param currentTeleporter we need this in order to check player's position and to teleport
     * @param currentPlayer we need this to check if the player is on teleporter's position
     * @return the teleporter on which the player is now
     */
    public Teleporter teleporting(Teleporter currentTeleporter, Player currentPlayer){
        if(currentTeleporter.getxPos() == currentPlayer.getxPos() &&
                currentTeleporter.getyPos() == currentPlayer.getyPos()){
            currentPlayer.setxPos(currentTeleporter.getPartner().getxPos());
            currentPlayer.setyPos(currentTeleporter.getPartner().getyPos());
        }
        return currentTeleporter.getPartner();
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    private void setxPos(int xPos) {
        this.xPos = xPos;
    }

    private void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private void setPartner(Teleporter partner) {
        this.partner = partner;
    }

    public Teleporter getPartner() {
        return partner;
    }

    @Override
    public String toString() {
        return String.format("Teleporter is at %d, %d.\n Teleporter's partner:\n%s", getxPos(), getyPos(), getPartner());
    }
}
