import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller to handle the game Win GUI. This displays score, changes to next game, and changes to main menu.
 * @author Lewis Pettifer
 *
 */
public class WinController {
	
	//GUI elements
	@FXML
	Label scoreText;
	@FXML
	Button nextBtn;
	@FXML
	Button menuBtn;
	
	private String level;
	private String user;

	/**
	 * Constructor
	 */
	public WinController() {
	}
	
	/**
	 * Sets needed variables
	 * @param level
	 * @param user
	 * @param seconds
	 */
	public void start(String level, String user, String seconds) {
		this.level=level;
		this.user=user;
		scoreText.setText("Your time was: "+seconds+" Seconds");
	}
	
	/**
	 * Changes scene to the next level.
	 * @param action of clicking
	 */
	@FXML
	public void nextClick(ActionEvent e) {
		//gets the number of the current level and adds one to get the next level
		System.out.println(level);
		level = level.substring(level.length()-5, level.length()-4);;
		int iLevel = Integer.parseInt(level);
		iLevel++;
		System.out.println(iLevel);
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
        loader.setController(new Manager("Level"+iLevel, user));
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
			System.out.println("Cant load level"+iLevel);
		}
        
	}
	
	/**
	 * Changes scene to main menu.
	 * @param Action of clicking
	 * @throws IOException unable to load scene
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
