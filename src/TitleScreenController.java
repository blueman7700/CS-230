import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TitleScreenController {

	@FXML
	Button startButton;
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void startClick(ActionEvent event) throws IOException{
		//loads new stage by swapping root
		Parent root;
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
		root = (Parent)loader.load();
		Manager controller = (Manager)loader.getController();		
		Scene scene = new Scene(root, 1920, 1080);
		controller.start(scene);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
}
