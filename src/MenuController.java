import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	Button startBtn;
	Button loadBtn;
	Button leaderBtn;
	Button drawBtn;
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void startClick(ActionEvent e) throws IOException {
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
        root = (Parent)loader.load();
        Manager controller = (Manager)loader.getController();
        Scene scene = new Scene(root, 1920, 1080);
        controller.start(scene, "Level1");
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void loadClick(ActionEvent e) {
		
	}
	
	@FXML
	public void leaderClick(ActionEvent e) {
		
	}
	
	@FXML
	public void drawClick(ActionEvent e) {
		
	}
}
