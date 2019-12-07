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

	//logged in user
	private String user;
	
	@FXML
	Button startBtn;
	Button loadBtn;
	Button leaderBtn;
	Button drawBtn;
	
	@FXML
	public void initialize() {
		
	}
	
	public void start(String user) {
		this.user = user;
	}
	
	@FXML
	public void startClick(ActionEvent e) throws IOException {
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
        loader.setController(new Manager("Level1", user));
        root = (Parent)loader.load();
        Manager controller = loader.getController();  
        Scene scene = new Scene(root, 1000, 1000);     
        controller.start(scene);        
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
