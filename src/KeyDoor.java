/**
 * This is the class for the door which needs a specific key
 * @author Nathan Preston
 * @version 1.0
 */
public class KeyDoor extends Tile{
	
	private String colour;//colour of door
	private String filePath = "sprites/Door"+colour+".png";//cant be static due to colour
	
	/**
	 * Gives a string and sets the colour of the door
	 * @param colour the String value for the colour of the door
	 */
	public KeyDoor(String colour) {
		this.colour = colour;
	}
	
	/**
	 * Returns the String value of the colour of the door
	 * @return colour of the door
	 */
	public String getColour() {
		return colour;
	}
	
	/**
	 * Gives a string and sets the colour of the door
	 * @param colour the String value for the colour of the door
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	/**
	 * Gets filePath.
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}
}
