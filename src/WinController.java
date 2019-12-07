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

public class WinController {
	
	@FXML
	Label scoreText;
	@FXML
	Button nextBtn;
	@FXML
	Button menuBtn;
	
	private String level;
	private String user;
	private String seconds;

	public WinController() {
	}
	
	public void start(String level, String user, String seconds) {
		this.level=level;
		this.user=user;
		this.seconds=seconds;
		System.out.println("Your time was: "+seconds+" Seconds");
		scoreText.setText("Your time was: "+seconds+" Seconds");
	}
	
	@FXML
	public void nextClick(ActionEvent e) {
		
	}
	
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
