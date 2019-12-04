
public class Wall extends Tile{
	
	private static String filePath = "sprites/wall.png";
	
	public Wall() {
		this.walkable = false;
	}

	public String getFilePath() {
		return filePath;
	}
}
