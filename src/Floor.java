public class Floor extends Tile {

	private Item contents;

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
}
