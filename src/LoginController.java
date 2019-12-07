import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	TextField textIn;
	@FXML
	Button loginBtn;
	@FXML
	Button deleteBtn;
	@FXML
	ListView<String> namesList;
	
	
	public LoginController() {};
	
	@FXML
    public void initialize() {
		
		try {
			ArrayList<String> names = Login.getUsers();
			ObservableList<String> items =FXCollections.observableArrayList (names);
			namesList.setItems(items);
			namesList.setPrefWidth(500);
			namesList.setPrefHeight(700);
		} catch (FileNotFoundException e2) {
			System.out.println("failed to display names");
		}
		
		
		
    }
	
	@FXML
	public void loginClick(ActionEvent e) throws IOException {
		String user = textIn.getText();
		
		if(!user.equals("")) {
			try {
				Login.loginTwo(user);
				//loads new stage by swapping root
		        Parent root;
		        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Menu.fxml"));
		        root = (Parent)loader.load();
		        MenuController controller = (MenuController)loader.getController();
		        Scene scene = new Scene(root, 1920, 1080);
		        controller.start(user);
		        stage.setScene(scene);
		        stage.show();
			} catch (Exception e1) {
				
			}
		}
		
		
	}
	
	@FXML
	public void deleteClick(ActionEvent e) {
		
	}
	
}
