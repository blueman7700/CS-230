import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class constructs and holds all elements within the game
 * @author Nathan Preston
 * @version 1.1
 */
public class Map {

	private Tile[][] level;//2d array of the map
	private int height;//map height
	private int width;//map width
	private String[] enemies;
	private int startX;//player's starting X cood
	private int startY;//player's starting Y cood
	private Manager gm;

	/**
	 * Create a new map instance.
	 *
	 * @param height 		height of the map.
	 * @param width 		width of the map.
	 * @param level 		array of tiles representing the map.
	 * @param startX 		starting x coordinate.
	 * @param startY 		starting y coordinate.
	 * @param enemiesList 	list of enemies on the map.
	 * @param gm 			game manager.
	 */
	public Map(int height, int width, Tile[][] level, int startX, int startY, ArrayList<String> enemiesList, Manager gm){
		this.height = height;
		this.width = width;
		this.level = level;	
		this.startX = startX;
		this.startY = startY;
		this.gm = gm;
		enemies = new String[enemiesList.size()];
		for(int x = 0; x < enemiesList.size(); x++) {
			enemies[x] = enemiesList.get(x);
		}
	}
	
	/**
	 * Checks for the type of door, then searches player's inventory for whether they have the necessary item(s),
	 * If the necessary items are found, the door becomes a floor
	 * @param d the door to be opened
	 * @param playerInv the player's inventory to be searched through
	 */
	public void openDoor(Tile d, LinkedList<Item> playerInv, int n) {
		int[] coods;

		//check if the door is a key door or a token door
		if(d instanceof KeyDoor) {
			coods = getTileCoods(d);
			KeyDoor kd = (KeyDoor) d;

			//check if the player has the correct key
			for(Item i : playerInv) {
				if(i instanceof Key) {
					Key k = (Key) i;
					//if the colours match then open the door
					if (kd.getColour().equals(k.getColour())) {
						gm.getPlayer().removeFromInv(k);
						level[coods[1]][coods[0]] = new Floor();
						break;
					}
				}
			}

		}else if (d instanceof TokenDoor) {
			coods = getTileCoods(d);
			TokenDoor td = (TokenDoor) d;

			//check if the player has the right number of tokens
			if (gm.getPlayer().getTokens() == td.getNum()) {
				level[coods[1]][coods[0]] = new Floor();
			}
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
	 * returns the list of all enemies
	 * @return String list of enemies' details
	 */
	public String[] getEnemies() {
		return enemies;
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
