/**
 * <b> Name: </b> Goal.java
 * <p>goal object.</p>
 * <br><b>Created:</b> 26/11/2019
 * <br><b>Last Modified:</b> 02/12/2019
 * @author Negrusa Sergiu
 * @version 1.0
 * <br><b>History:</b>
 * <p>Version 1.0 (26/11/2019)</p>
 */

public class Goal extends Tile{

    private int xPos;
    private int yPos;
    private static String filePath = "sprites/goal.png";

    /**
     * create an instance of Goal
     */
    public Goal(){
        setxPos(xPos);
        setyPos(yPos);
    }

    /**
     * method that checks if the player reaches the goal
     *
     * @param player we need player in order to check his position
     */
    public void interact(Player player){
        if(player.getxPos() == this.getxPos() && player.getyPos() == this.getyPos()){
            System.out.println("You WIN!");
        }
    }

    private void setxPos(int xPos) {
        this.xPos = xPos;
    }

    private void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public String getFilePath() {
    	return filePath;
    }

    /**
     * convert goal object to string.
     *
     * @return goal as string.
     */
    @Override
    public String toString() {
        return String.format("Goal is at %d, %d.\n", getxPos(), getyPos());
    }
}
