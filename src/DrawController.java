

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class handles the custom drawing functionality. This controller links with draw.fxml
 * @author lewis
 *
 */
public class DrawController {
	
	//GUI element IDs
	@FXML
	RadioButton bScribble;
	@FXML
	RadioButton bSpray;
	@FXML
	RadioButton bLine;
	@FXML
	RadioButton bCircle;
	@FXML
	RadioButton bSquare;
	@FXML
	RadioButton bText;
	@FXML
	ToggleGroup tg = new ToggleGroup();
	@FXML
	Text sizeText;
	@FXML
	Slider sizeSlider;
	@FXML
	Text colourText;
	@FXML
	ColorPicker colourPicker;
	@FXML
	Button saveButton;
	@FXML
	Button exitButton;
	@FXML
	Canvas canvas;
	@FXML
	TextField text;
	
	//Records what drawing tool is selected
	private char tool;
	//Records the coords of a click action
	private double x;
	private double y;
	//The ID of currently logged in user
	private String userID;

	@FXML
	/**
	 * This function is run aster construction and any GUIs
	 * elements that need to be assigned are done so
	 */
	public void initialize() {

		GraphicsContext graphic = canvas.getGraphicsContext2D();
		
		sizeSlider.setMin(2.0);
		
		//Sets tool to scribble
		bScribble.setOnAction((event) -> {
			tool = 's';
			sizeSlider.setMax(50.0);
			text.setDisable(true);
		});
		//sets tool to the spray paint
		bSpray.setOnAction((event) -> {
			tool = 'p';
			sizeSlider.setMax(50.0);
			text.setDisable(true);
		});
		//sets tool to line
		bLine.setOnAction((event) -> {
			tool = 'l';
			sizeSlider.setMax(20.0);
			text.setDisable(true);
		});
		//sets tool to circle
		bCircle.setOnAction((event) -> {
			tool = 'c';
			sizeSlider.setMax(50.0);
			text.setDisable(true);
		});
		//sets tool to Square
		bSquare.setOnAction((event) -> {
			tool = 'S';
			sizeSlider.setMax(50.0);
			text.setDisable(true);
		});
		//sets tool to Text
		bText.setOnAction((event) -> {
			tool = 't';
			text.setDisable(false);
		});
		
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			/**
			 * Gets the X and Y coords of when mouse is clicked
			 * @param e The event of mouse press
			 */
			@Override
			public void handle(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}			
		});
		
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			/**
			 * Gets the X and Y coords when the mouse id released and draws the current selected shape
			 * @param e The event of mouse release
			 */
			@Override
			public void handle(MouseEvent e) {
				if(tool == 'l') {
					drawLine(x, y, e.getX(), e.getY());
				}
				else if(tool == 'c') {
					drawCircle(x, y, e.getX(), e.getY());					
				}	
				else if(tool == 'S') {
					drawSquare(x, y, e.getX(), e.getY());			
				}
				else if(tool == 't') {
					graphic.setFill(colourPicker.getValue());
					graphic.fillText(text.getText(), x, y, e.getX() - x);
				}
			}
		});
		
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			/**
			 * Gets the X and Y coords when the mouse if pressed and dragged.
			 * If scribble or spray can are selected then they are drawn at this time
			 * @param e The event of click and drag
			 */
			@Override
			public void handle(MouseEvent e) {				
				if(tool == 's') {
					drawScribble(e.getX(), e.getY());
				}else if(tool == 'p') {
					drawSpray(e.getX(), e.getY());
				}
			}		
		});
		
		saveButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			/**
			 * Saves the canvas and changes scene when button is pressed
			 * @param e The event of button press
			 */
			@Override
			public void handle(ActionEvent e) {
    	        saveImage();
    	        changeScene(e);
			}
		});
		
		exitButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			/**
			 * Changes the scene when button is pressed
			 * @param e The event of button press
			 */
			@Override
			public void handle(ActionEvent e) {
				changeScene(e);
			}
		});
		
	}
	
	/**
	 * Sets the current user drawing
	 * @param user
	 */
	public void start(String user) {
		this.userID = user;
	}
	
	/**
	 * Function to draw a oval on the canvas
	 * @param x The x coord to draw from
	 * @param y The y coord to draw from
	 * @param x2 The x coord to draw to
	 * @param y2 The y coord to draw to
	 */
	private void drawCircle(double x, double y, double x2, double y2) {		
		GraphicsContext graphic = canvas.getGraphicsContext2D();		
		if(x > x2 && y > y2) {		
			graphic.setFill(colourPicker.getValue());
			graphic.fillOval(x2, y2, x - x2, y - y2);		
		}
		else if(x > x2) {			
			graphic.setFill(colourPicker.getValue());
			graphic.fillOval(x2, y, x - x2, y2 - y);			
		}
		else if(y > y2) {			
			graphic.setFill(colourPicker.getValue());
			graphic.fillOval(x, y2, x2 - x, y - y2);			
		}
		else{
			graphic.setFill(colourPicker.getValue());
			graphic.fillOval(x, y, x2 - x, y2 - y);
		}
		
	}
	
	/**
	 * Draws a rectangle on the canvas
	 * @param x The x coord to draw from
	 * @param y The y coord to draw from
	 * @param x2 The x coord to draw to
	 * @param y2 The y coord to draw to
	 */
	private void drawSquare(double x, double y, double x2, double y2) {
		GraphicsContext graphic = canvas.getGraphicsContext2D();		
		if(x > x2 && y > y2) {		
			graphic.setFill(colourPicker.getValue());
			graphic.fillRect(x2, y2, x - x2, y - y2);		
		}
		else if(x > x2) {			
			graphic.setFill(colourPicker.getValue());
			graphic.fillRect(x2, y, x - x2, y2 - y);			
		}
		else if(y > y2) {			
			graphic.setFill(colourPicker.getValue());
			graphic.fillRect(x, y2, x2 - x, y - y2);			
		}
		else{
			graphic.setFill(colourPicker.getValue());
			graphic.fillRect(x, y, x2 - x, y2 - y);
		}
	}
	
	/**
	 * Draws a straight line on canvas
	 * @param x The x coord to draw from
	 * @param y The y coord to draw from
	 * @param x2 The x coord to draw to
	 * @param y2 The y coord to draw to
	 */
	private void drawLine(double x, double y, double x2, double y2) {
		if(x != x2 && y != y2) {
			GraphicsContext graphic = canvas.getGraphicsContext2D();
			graphic.setStroke(colourPicker.getValue());
			graphic.setLineWidth(sizeSlider.getValue());
			graphic.strokeLine(x, y, x2, y2);
		}		
	}
	
	/**
	 * Saves the current canvas to a png file called avatar and the current userID
	 * @param userID the currently logged in user
	 */
	private void saveImage() {
		WritableImage picture = new WritableImage(50, 50);
        File outputFile = new File("src/Files/"+userID+".png");
        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);
        try {
        	ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(sp, picture), null), "png", outputFile);
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
	}
	
	/**
	 * Function to change the current scene
	 * @param sceneLocation The scene to be changed to
	 * @param e The action event from the GUI that want to change scene
	 */
	private void changeScene(ActionEvent e) { 	        
		//loads new stage by swapping root
		Parent root;
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Menu.fxml"));
		try {
			root = (Parent)loader.load();
			MenuController controller = (MenuController)loader.getController();
			Scene scene = new Scene(root, 1000, 1000);
			controller.start(userID);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			System.out.println("Cant change scene");
		}
		
	}
	
	/**
	 * Function that draws a centred circle around current mouse coords
	 * @param x The x ccord of the mouse
	 * @param y The y coord if the mouse
	 */
	private void drawScribble(double x, double y) {
		GraphicsContext graphic = canvas.getGraphicsContext2D();
		graphic.setFill(colourPicker.getValue());
		graphic.fillOval(x - ( sizeSlider.getValue()/2), y - ( sizeSlider.getValue()/2), 
				sizeSlider.getValue(), sizeSlider.getValue());
	}
	
	/**
	 * Function that draws four random location circles within a square of a given size
	 * @param x The x coord of the random centre
	 * @param y The y coord of the random centre
	 */
	private void drawSpray(double x, double y) {
		GraphicsContext graphic = canvas.getGraphicsContext2D();
		graphic.setFill(colourPicker.getValue());
		for(int i = 0; i < 4; i++) {
			graphic.fillOval(x + ThreadLocalRandom.current().nextDouble(sizeSlider.getValue() * -1
				, sizeSlider.getValue()) , y + ThreadLocalRandom.current().
				nextDouble(sizeSlider.getValue() * -1 , sizeSlider.getValue()), 2, 2);
		}
		
	}
}
