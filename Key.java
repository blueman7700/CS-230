/**
 * Class of the Key that the user picks up to open a door
 * @author Nathan
 * @version 1.0
 */
public class Key extends Item {
	
	//Colour of the key
	private String colour = new String();
	
	/**
	 * @param name of the item (Key)
	 * @param colour of the key
	 */
	public Key(String name, String colour) {
		super(name);
		this.colour = colour;
	}
	
	/**
	 * get the colour of the key
	 * @return key colour
	 */
	public String getColour() {
		return colour;
	}
	
	/**
	 * Changes the colour of the key
	 * @param new key colour
	 */
	private void setColour(String colour) {
		this.colour = colour;
	}
}
