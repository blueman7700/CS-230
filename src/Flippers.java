/**
 * Class of the Flippers that the user picks up to be able to walk through water
 * @author Nathan
 * @version 1.0
 */
public class Flippers extends Item {

	private static String filepath = "sprites/Flipper.png";
	
	/**
	 * Constructor
	 */
	public Flippers() {
		
	}
	
	/**
	 * gets the filepath to the class' sprite
	 * @return string value of the class' sprite
	 */
	public String getFilepath() {
		return filepath;
	}
}
