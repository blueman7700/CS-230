import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {

	//logged in user
	private String user;
	private String message;
	private Timer timer = new Timer();


	@FXML
	Button startBtn;
	@FXML
	Button loadBtn;
	@FXML
	Button leaderBtn;
	@FXML
	Button drawBtn;
	@FXML
	private Label motd;

	@FXML
	public void initialize() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		fxmlLoader.setController(this);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> changeMessage());
			}
		}, 0, 30000);

	}

	public void start(String user) {
		this.user = user;
	}

	@FXML
	public void startClick(ActionEvent e) throws IOException {
		//loads new stage by swapping root
		Parent root;
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
		loader.setController(new Manager("Level1", user));
		root = (Parent) loader.load();
		Manager controller = loader.getController();
		Scene scene = new Scene(root, 1000, 1000);
		controller.start(scene);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void loadClick(ActionEvent e) {
		
		//loads new stage by swapping root
		Parent root;
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
		loader.setController(new Manager(user+"1", user));
		try {
			root = (Parent) loader.load();
			Manager controller = loader.getController();
			Scene scene = new Scene(root, 1000, 1000);
			controller.start(scene);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Cant load user level");
		}
		
		
	}

	@FXML
	public void leaderClick(ActionEvent e) {

	}

	@FXML
	public void drawClick(ActionEvent e) {

	}

	private void changeMessage() {

		String msg;

		try {
			msg = MessageOfTheDay.getMessage();
		}catch (Exception e) {
			msg = "test";
		}

		this.motd.setText(msg);
	}
}
