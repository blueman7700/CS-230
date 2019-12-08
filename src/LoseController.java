import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller to handle game Lose GUI. This can restart the failed level or go to main menu.
 * @author Lewis Pettifer
 *
 */
public class LoseController {
	
	//GUI elements
	@FXML
	Label scoreText;
	@FXML
	Button restartBtn;
	@FXML
	Button menuBtn;
	
	private String level;
	private String user;

	/**
	 * Constructor
	 */
	public LoseController() {
		
	}
	
	/**
	 * Sets the needed variables.
	 * @param Current Level
	 * @param Current user
	 * @param seconds
	 */
	public void start(String level, String user, String seconds) {
		this.level=level;
		this.user=user;
		scoreText.setText("You lasted: "+seconds+" Seconds");
	}
	/**
	 * Loads the main game GUI from the beginning.
	 * @param Action of clicking
	 */
	@FXML
	public void restartClick(ActionEvent e) {
		//gets the number of the current level
		level = level.substring(level.length()-5, level.length()-4);
		System.out.print("Level"+level);
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
        loader.setController(new Manager("Level"+level, user));
        try {
			root = (Parent)loader.load();
			Manager controller = loader.getController();  
			Scene scene = new Scene(root, 1000, 1000);     
			controller.start(scene);        
			stage.setScene(scene);
			stage.show();	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Cant load level"+level);
		}
	}
	
	/**
	 * Changes scene to the main menu.
	 * @param Action of clicking
	 * @throws IOException failed to change scene
	 */
	@FXML
	public void menuClick(ActionEvent e) throws IOException {
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Menu.fxml"));
        root = (Parent)loader.load();
        MenuController controller = (MenuController)loader.getController();
        Scene scene = new Scene(root, 1000, 1000);
        controller.start(user);
        stage.setScene(scene);
        stage.show();
	}
}
