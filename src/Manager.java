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
	private static int GRID_CELL_WIDTH = 50;
	private static int GRID_CELL_HEIGHT = 50;
	// Loaded images
	Image player;
	Image dirt;
	// X and Y coordinate of player
	int playerX = 1;
	int playerY = 1;
	
	@FXML
	private Canvas gameCanvas;
	@FXML
	public void initialize() {
		// Load images
		player = new Image("sprites/player.png");
		dirt = new Image("sprites/dirt.png");
		//gameCanvas.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
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
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				gc.drawImage(dirt, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);	
			}
		}
		
		// Draw player at current location
		gc.drawImage(player, playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);
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
	        	playerX = playerX + 1;
	        	break;
		    case A:
		    	//Left key was pressed. So move the player left by one cell.
		    	playerX = playerX -1;
		    	break;
		    case W:
		    	//Up key was pressed so move the player up by one cell.
		    	playerY = playerY -1;
		    	break;
		    case S:
		    	//Down key was pressed so move the player down by one cell.
		    	playerY = playerY +1;
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

		//todo: implement getMap().
		return null;
	}
}
