/**
 * This class constructs and holds all elements within the game
 * @author Nathan Preston
 * @version 1.0
 */
public class Map {

	private Tile[][] level;//2d array of the map
	private int height;//map height
	private int width;//map width
	private int startX;//player's starting X coord
	private int startY;//player's starting Y coord
	
	/**
	 * This constructs the map, by creating a 2d array and filling it with Tiles
	 * @param level 2D tile array representing the map.
	 * @param startX player start position.
	 * @param startY player start position.
	 */
	public Map(Tile[][] level, int startX, int startY){

		//calculate height and width from the tile array
		this.height = level.length;
		this.width = level[0].length;
		this.level = level;	
		this.startX = startX;
		this.startY = startY;
	}
	
	/**
	 * Checks for the type of door, then searches player's inventory for whether they have the necessary item(s)
	 * @param d the door aimed to be opened
	 * @param k TESTING
	 */
	public void openDoor(Tile d, Key k, int n) {
		int posX;
		int posY;

		//check door type
		if(d instanceof KeyDoor && k.getColour().equals(((KeyDoor) d).getColour())) {
			posX = getTileCoods(d)[0];
			posY = getTileCoods(d)[1];
			level[posX][posY] = new Floor();
		} else if (d instanceof TokenDoor && n == ((TokenDoor) d).getNum()) {
			posX = getTileCoods(d)[0];
			posY = getTileCoods(d)[1];
			level[posX][posY] = new Floor();
		}
	}
	
	/**
	 * Returns the coodinates of a Tile found in the map 
	 * @param t the Tile to be found
	 * @return an array of the X and Y coodinate
	 */
	public int[] getTileCoods(Tile t) {
		int[] coords = new int[2];
		
		for(int y = 0; y < level.length; y++) {
			for(int x = 0; x < level[0].length; x++) {
				if(level[y][x].equals(t)) {
					coords[0] = x;
					coords[1] = y;
				}
			}
		}
		
		return coords;
	}
	
	/**
	 * returns the type of tile that is contained at that location
	 * @param x the X cood
	 * @param y the Y cood
	 * @return the Tile stored
	 */
	public Tile getTile(int x, int y) {
		return level[y][x];
	}
	
	/**
	 * gets the height of the map
	 * @return int value of the map height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * gets the width of the map
	 * @return int value of the map width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Overrides the type of tile stored in that location
	 * @param t the tile to be stored
	 * @param x the x cood
	 * @param y the y cood
	 */
	public void setTile(Tile t, int x, int y) {
		level[y][x] = t;
	}
	
	/**
	 * gets the X coodinate of where the player starts
	 * @return int value of X coodinate
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * gets the Y coodinate of where the player starts
	 * @return int value of Y coodinate
	 */
	public int getStartY() {
		return startY;
	}

}
