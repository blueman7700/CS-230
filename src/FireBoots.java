/**
 * Class of the FireBoots that the user picks up to be able to walk through fire
 * @author Nathan
 * @version 1.0
 */
public class FireBoots extends Item {

	private static String filepath = "sprites/FireBoots.png";
	/**
	 * @param name of the item (FireBoots)
	 */
	public FireBoots(String name) {
		super(name);
	}
	
	/**
	 * gets the filepath to the class' sprite
	 * @return string value of the class' sprite
	 */
	public String getFilepath() {
		return filepath;
	}
}
