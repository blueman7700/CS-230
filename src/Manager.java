import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import sprites.*;
import views.*;

//The manager class
public class Manager {

	// The size of each cell
	private static int GRID_CELL_SIZE = 50;
	// Load an image
	private Image tile;
	private Image playerImg;
	private Image dirt;
	//the player
	private Player player;
	//all the enemies
	private ArrayList<String> enemiesList = new ArrayList<>();
	//the map
	private Map map;
	//File reader
	private FileReader fr;
	
	@FXML
	private Canvas gameCanvas;
	@FXML
	public void initialize() {
		Experiment ex = new Experiment();
		
		//load filereader
		fr = new FileReader("src/Files/Level1.txt");
		//loads the enemies
		
		//loads the map and sets the tiles
		map = new Map(fr.getHeight(), fr.getWidth(), fr.fileToArray(), fr.getStartX(), fr.getStartY(), enemiesList);
		//load the player
		player = new Player(fr.getStartX(), fr.getStartY(), this);
		// Load images
		playerImg = new Image("sprites/player.png");
		dirt = new Image("sprites/dirt.png");
		
		gameCanvas.setWidth(map.getWidth() * GRID_CELL_SIZE);
		gameCanvas.setHeight(map.getHeight() * GRID_CELL_SIZE);
		
		drawGame();
	}
	
	public void start(Scene scene) {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
	}
	
	/**
	 * Draw the game on the canvas.
	 */
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = gameCanvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

		// Draw row of dirt images
		// We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
		// We draw the row at y value 2.
		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				tile = new Image(map.getTile(x, y).getFilePath());
				gc.drawImage(tile, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE);
				if (( (map.getTile(x, y))).contains() == true) {
					//checks to see what the contents is then add is
                	if (((Floor) (map.getTile(x, y))).getKey() != null) {
                		tile = new Image("sprites/Key"+ ((Floor) map.getTile(x, y)).getKey().getColour() +".png");
                	}
                	if (((Floor) (map.getTile(x, y))).getToken() != null) {
                		tile = new Image("sprites/Token.png");
                	}
                	if (((Floor) (map.getTile(x, y))).getFlippers() != null) {
                		tile = new Image("sprites/Flippers.png");
                	}
                	if (((Floor) (map.getTile(x, y))).getFireBoots() != null) {
                		tile = new Image("sprites/Boots.png");
                	}
					gc.drawImage(tile, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE);
				}
			}
		}
		
		// Draw player at current location
		gc.drawImage(playerImg, player.getxPos() * GRID_CELL_SIZE, player.getyPos() * GRID_CELL_SIZE);
	}
	
	/**
	 * Called when a key is pressed, moves player according to WASD key pressed.
	 * Use WASD to move.
	 * @param event the action of a key press
	 */
	public void processKeyEvent(KeyEvent event) {
		switch (event.getCode()) {
			
		    case D:
		    	// Right key was pressed. So move the player right by one cell.
	        	player.move(MoveType.RIGHT);
	        	//sees if a player is on an item then removes it if so
	        	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	        		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
	        	break;
		    case A:
		    	//Left key was pressed. So move the player left by one cell.
		    	player.move(MoveType.LEFT);
		    	//sees if a player is on an item then removes it if so
		    	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	        		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
		    	break;
		    case W:
		    	//Up key was pressed so move the player up by one cell.
		    	player.move(MoveType.DOWN);
		    	//sees if a player is on an item then removes it if so
		    	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	        		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
		    	break;
		    case S:
		    	//Down key was pressed so move the player down by one cell.
		    	player.move(MoveType.UP);
		    	//sees if a player is on an item then removes it if so
		    	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	       		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
		    	break;
	        default:
	        	// Do nothing
	        	break;
		}
		
		// Redraw game as the player may have moved.
		drawGame();
		
		// Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
		event.consume();
	}

	/**
	 * get the game map.
	 *
	 * @return game map.
	 */

	public Map getMap() {
		return map;
	}

	/**
	 * get the player.
	 *
	 * @return player
	 */

	public Player getPlayer() {
		return player;
	}
}
