
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
