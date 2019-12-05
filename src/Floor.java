public class Floor extends Tile {

	private Key key;
	private Token token;
	private Flippers flip;
	private FireBoots boot;
	private Boolean contains = false;
	private static String filePath = "sprites/floor.png";

	public Floor() {
	}

	public Key getKey() {
		return this.key;
	}
	
	public Token getToken() {
		return this.token;
	}
	
	public Flippers getFlippers() {
		return this.flip;
	}
	
	public FireBoots getFireBoots() {
		return this.boot;
	}
	
	public Boolean contains() {
		return contains;
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

	public String getFilePath() {
		return filePath;
	}
}
