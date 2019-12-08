/**
 * This class is the Tile superclass, for all items that can be picked up by the user
 * The class is empty but certain Tiles will contain type Item, which all pickups are a
 * subclass of. Making it an easier method of containing an item in a tile.
 * 
 * @author Lewis Pettifer
 *
 */
public class Tile {
	
	
	protected boolean walkable;
	private static String filePath = "sprites/wall.png";
	
	public boolean getWalkable() {
		return this.walkable;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public Boolean contains() {
		return false;
	}
	
	public void setWalkable(Boolean w) {
		this.walkable = w;
	}
	
	
}
