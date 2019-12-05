/**
 * Class of the Token that the user picks up to open a door
 * @author Nathan
 * @version 1.0
 */
public class Token extends Item {

	private static String filepath = "sprites/Token.png";
	
	/**
	 * @param name of the item (Token)
	 */
	public Token() {
	}
	
	/**
	 * gets the filepath to the class' sprite
	 * @return string value of the class' sprite
	 */
	public String getFilepath() {
		return filepath;
	}
	
}
