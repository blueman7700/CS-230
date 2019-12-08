/**
 * This class is the Tile superclass, for all items that can be picked up by the user
 * The class is empty but certain Tiles will contain type Item, which all pickups are a
 * subclass of. Making it an easier method of containing an item in a tile.
 * 
 * @author Lewis Pettifer
 * @version 1.0
 */
public class Tile {
	
	
	protected boolean walkable;
	private static String filePath = "sprites/wall.png";

	/**
	 * check if the tile is walkable.
	 *
	 * @return walkable boolean.
	 */
	public boolean getWalkable() {
		return this.walkable;
	}

	/**
	 * get the file path of the current tile sprite.
	 *
	 * @return file path.
	 */
	public String getFilePath() {
		return filePath;
	}

	public Boolean contains() {
		return false;
	}

	/**
	 * set whether the tile is walkable or not.
	 *
	 * @param w walkable boolean.
	 */
	public void setWalkable(Boolean w) {
		this.walkable = w;
	}
	
	
}
