/**
 * This class constructs and holds all elements within the game
 * @author Nathan Preston
 * @version 1.0
 */
public class Map {

	private Tile[][] level;//2d array of the map
	private int height;//map height
	private int width;//map width
	private int startX;//player's starting X cood
	private int startY;//player's starting Y cood
	
	/**
	 * This constructs the map, by creating a 2d array and filling it with Tiles
	 * @param height of the map
	 * @param width of the map
	 * @param strTiles a string of all tiles
	 */
	public Map(int height, int width, Tile[][] level, int startX, int startY){
		this.height = height;
		this.width = width;
		this.level = level;	
		this.startX = startX;
		this.startY = startY;
	}
	
	
	/**
	 * Checks for the type of door, then searches player's inventory for whether they have the necessary item(s)
	 * @param d the door aimed to be opened
	 * @param k TESTING
	 */
	public void openDoor(Object d, Key k, int n) {
		int[] coods = new int[2];
		//SEARCH THROUGH INVENTORY
		if(d instanceof KeyDoor && k.getColour() == ((KeyDoor) d).getColour()) {
			coods = getTileCoods((Tile) d);
			level[coods[1]][coods[0]] = new Floor();
		} else if (d instanceof TokenDoor && n == ((TokenDoor) d).getNum()) {
			coods = getTileCoods((Tile) d);
			level[coods[1]][coods[0]] = new Floor();
		}
	}
	
	/**
	 * Returns the coodinates of a Tile found in the map 
	 * @param t the Tile to be found
	 * @return an array of the X and Y coodinate
	 */
	public int[] getTileCoods(Tile t) {
		int[] coods = new int[2];
		
		for(int y = 0; y < level.length; y++) {
			for(int x = 0; x < level[0].length; x++) {
				if(level[y][x].equals(t)) {
					coods[0] = x;
					coods[1] = y;
				}
			}
		}
		
		return coods;
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
