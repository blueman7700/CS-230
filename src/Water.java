/**
 * Water tile. Blocks player movement if they dont have Flippers.
 * @author Lewis Pettifer
 *
 */
public class Water extends Tile{

	private static String filePath = "sprites/water.png";

	/**
	 * get the file path of the water sprite.
	 *
	 * @return file path.
	 */
	public String getFilePath() {
		return filePath;
	}
	
}
