/**
 * This is the class for the door which needs a set number of tokens to open
 * @author Nathan
 * @version 1.0
 */
public class TokenDoor extends Tile {
	private int num;//number of tokens needed to open this door
	
	/**
	 * sets the number of tokens needed to open the door
	 * @param num the number of tokens
	 */
	public TokenDoor(int num) {
		this.num = num;
	}
	
	/**
	 * returns the number of tokens needed
	 * @return the 
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * sets the number of tokens needed to open the door
	 * @param num the number of tokens
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
}
