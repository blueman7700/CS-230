import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
	int playerX = 0;
	int playerY = 0;
	
	@FXML
	private Canvas gameCanvas;
	@FXML
	public void initialize() {
		// Load images
		player = new Image("sprites/player.png");
		dirt = new Image("sprites/dirt.png");
		
		drawGame();
	}
	
	public Manager() {
		//null
	}
	
	/**
	 * Draw the game on the canvas.
	 */
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = gameCanvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

		// Draw row of dirt images
		// We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
		// We draw the row at y value 2.
		for (int x = 0; x < 5; x++) {
			gc.drawImage(dirt, x * GRID_CELL_WIDTH, 2 * GRID_CELL_HEIGHT);	
		}
		
		// Draw player at current location
		gc.drawImage(player, playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);			
	}
}
