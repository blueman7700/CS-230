public class Floor extends Tile {

	private Item contents;
	private static String filePath = "sprites/floor.png";

	public Floor() {

	    contents =  null;
	}

	public Item getContents() {

		return this.contents;
	}

	/**
	 * set the content of the floor tile.
	 *
	 * @param i item that the floor will contain.
	 */

	public void setContent(Item i) {
		contents = i;
	}

	/**
	 * check if the tile can be moved to by the AI.
	 *
	 * @return walkable.
	 */

	@Override
	public boolean getWalkable() {

		return (this.contents == null);
	}

	public String getFilePath() {
		return filePath;
	}
}
