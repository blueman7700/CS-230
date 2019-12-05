/**
 * The walkable floor tile, which can contain an item
 * @author Lewis
 * @version 1.2
 */
public class Floor extends Tile {

	private Key key;
	private Token token;
	private Flippers flip;// All the possible items to be stored
	private FireBoots boot;
	private Boolean contains = false;//false if nothing is contained, true if there is something
	private static String filePath = "sprites/floor.png";

	/**
	 * return's the key stored
	 * @return Key object
	 */
	public Key getKey() {
		return this.key;
	}

	/**
	 * return's the token stored
	 * @return Token object
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 * return's the Flippers stored
	 * @return Flipper object
	 */
	public Flippers getFlippers() {
		return this.flip;
	}

	/**
	 * return's the Fire Boots stored
	 * @return FireBoot object
	 */
	public FireBoots getFireBoots() {
		return this.boot;
	}

	/**
	 * Checks whether the Floor contains anything
	 * @return the Boolean contains attribute
	 */
	public Boolean contains() {
		return contains;
	}

	/**
	 * Checks what has been stored, and returns the contents
	 * @return Key/Token/Flippers/FireBoots Object
	 */
	public Item getContents() {
		if(!isNull(key)) {
			return key;
		} else if(!isNull(token)) {
			return token;
		} else if(!isNull(flip)) {
			return flip;
		} else {
			return boot;
		}
	}

	/**
	 * set the content of the floor tile.
	 *
	 * @param i key that the floor will contain.
	 */

	public void setContent(Key i) {
		key = i;
		contains = true;
	}

	/**
	 * set the content of the floor tile.
	 *
	 * @param i token that the floor will contain.
	 */

	public void setContent(Token i) {
		token = i;
		contains = true;
	}

	/**
	 * set the content of the floor tile.
	 *
	 * @param i flippers that the floor will contain.
	 */

	public void setContent(Flippers i) {
		flip = i;
		contains = true;
	}

	/**
	 * set the content of the floor tile.
	 *
	 * @param i boots that the floor will contain.
	 */

	public void setContent(FireBoots i) {
		boot = i;
		contains = true;
	}

	/**
	 * check if the tile can be moved to by the AI.
	 *
	 * @return walkable.
	 */

	@Override
	public boolean getWalkable() {

		return (this.key == null || this.token == null || this.flip == null || this.boot == null);
	}

	/**
	 * Returns the filepath of the texture
	 * @return filePath String value of the filepath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * checks whether an object stored in a variable is null
	 * @param i variable of type Item
	 * @return True if is null, False otherwise
	 */
	public boolean isNull(Item i) {
		if(i == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the contents of the floor
	 */
	public void removeContents() {
		key = null;
		token = null;
		flip = null;
		boot = null;
	}
}
