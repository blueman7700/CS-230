/**
 * A wall type of tile. Doesn't let player or AI's to move onto it.
 * Can't contain items.
 * @author Lewis Pettifer
 *
 */
public class Wall extends Tile{
	
	private static String filePath = "sprites/wall.png";

	/**
	 * create a new wall instance.
	 */
	public Wall() {
		this.walkable = false;
	}

	/**
	 * get the file path of te wall sprite.
	 *
	 * @return file path.
	 */
	public String getFilePath() {
		return filePath;
	}
}
