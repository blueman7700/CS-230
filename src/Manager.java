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
	Image tile;
	Image playerImg;
	Image dirt;
	//the player
	Player player;
	//the map
	Map map;
	//File reader
	FileReader fr;
	
	@FXML
	private Canvas gameCanvas;
	@FXML
	public void initialize() {
		//load filereader
		fr = new FileReader("src/ExampleFile.txt");
		//loads the map and sets the tiles
		map = new Map(fr.getHeight(), fr.getWidth(), fr.fileToArray(), fr.getStartX(), fr.getStartY());
		//load the player
		player = new Player("sprites/player.png", fr.getStartX(), fr.getStartY(), this);
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
				tile = new Image(map.getTile(x, y).getSprite());
				gc.drawImage(tile, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE);	
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
	        	break;
		    case A:
		    	//Left key was pressed. So move the player left by one cell.
		    	player.move(MoveType.LEFT);
		    	break;
		    case W:
		    	//Up key was pressed so move the player up by one cell.
		    	player.move(MoveType.DOWN);
		    	break;
		    case S:
		    	//Down key was pressed so move the player down by one cell.
		    	player.move(MoveType.UP);
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
}
