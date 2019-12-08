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
    private static String filePath = "sprites/Teleporter.png";
    private Boolean walkable = true;

    /**
     * create an instance of Teleporter.
     *
     * @param xPos    x position of the teleporter
     * @param yPos    y position of the teleporter
     */
    public Teleporter(int xPos, int yPos){
        setPos(xPos, yPos);
    }

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

    public void setPartner(Teleporter partner) {
        this.partner = partner;
    }

    public Teleporter getPartner() {
        return partner;
    }
    
    public String getFilePath() {
    	return filePath;
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
