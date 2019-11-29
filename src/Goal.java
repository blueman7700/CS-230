/**
 * <b> Name: </b> Goal.java
 * <p>Player object.</p>
 * <br><b>Created:</b> 26/11/2019
 * <br><b>Last Modified:</b> 26/11/2019
 * @author Negrusa Sergiu
 * @version 1.0
 * <br><b>History:</b>
 * 	<li>Version 1.0 (26/11/2019)</li>
 * </ul>
 */

public class Goal {
    private int xPos;
    private int yPos;

    /**
     * create an instance of Goal
     * @param xPos x coordonate of the goal
     * @param yPos y coodonate of the goal
     */
    public Goal(int xPos, int yPos){
        setxPos(xPos);
        setyPos(yPos);
    }

    /**
     * method that checks if the player reaches the goal
     * @param player we need player in order to check his position
     * @param goal we need goal's position to check the player
     */
    public void victory(Player player, Goal goal){
        if(player.getxPos() == goal.getxPos() && player.getyPos() == goal.getyPos()){
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

    @Override
    public String toString() {
        return String.format("Goal is at %d, %d.\n", getxPos(), getyPos());
    }
}
