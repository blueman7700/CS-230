/**
 * A wall type of tile. Doesn't let player or AI's to move onto it.
 * Can't contain items.
 * @author Lewis Pettifer
 *
 */
public class Wall extends Tile{
	
	private static String filePath = "sprites/wall.png";
	
	public Wall() {
		this.walkable = false;
	}

	public String getFilePath() {
		return filePath;
	}
}
