import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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

/**
 * Controller to handle the main menu GUI.
 * Allows to start game, load save, see leader boards, draw avatar, and logout.
 * Also will display a message of the day.
 * @author Lewis Pettifer
 *
 */
public class MenuController {

	//logged in user
	private String user;
	private String message;
	private Timer timer = new Timer();

	//GUI Elements
	@FXML
	Button startBtn;
	@FXML
	Button loadBtn;
	@FXML
	Button leaderBtn;
	@FXML
	Button drawBtn;
	@FXML
	Button logBtn;
	@FXML
	private Label motd;

	/**
	 * initalizes the controller
	 */
	@FXML
	public void initialize() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		fxmlLoader.setController(this);
		//loads message of the day on a timer
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> changeMessage());
			}
		}, 0, 30000);

	}
	
	/**
	 * sets needed variables
	 * @param user
	 */
	public void start(String user) {
		this.user = user;
	}

	/**
	 * Changes scene to main game
	 * @param e Action of clicking
	 * @throws IOException can't change scene
	 */
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

	/**
	 * Changes scene to main game but with a user level
	 * @param e Action of clicking
	 */
	@FXML
	public void loadClick(ActionEvent e) {
		
		File f = new File("src/Files/"+user+".txt");
		
		try {
			//Scanner checks to see if the user has a save
			Scanner in = new Scanner(f);
			//loads new stage by swapping root
			Parent root;
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
			loader.setController(new Manager(user, user));
			root = (Parent) loader.load();
			Manager controller = loader.getController();
			Scene scene = new Scene(root, 1000, 1000);
			controller.start(scene);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			System.out.println("Not Found");	//if file not found, throw exception
			
		}
		
	}

	/**
	 * Changes scene to leader boards
	 * @param e action of clicking
	 * @throws IOException can't chnage scene
	 */
	@FXML
	public void leaderClick(ActionEvent e) throws IOException{
		//loads new stage by swapping root
		Parent root;
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Leaderboard.fxml"));
		root = (Parent)loader.load();
		LeaderboardController controller = (LeaderboardController)loader.getController();
		Scene scene = new Scene(root, 1000, 1000);
		controller.start(user);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Changes scene to drawing
	 * @param e Action of clicking
	 */
	@FXML
	public void drawClick(ActionEvent e) {
		//Changes to draw GUI
		Parent root;
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Draw.fxml"));
		try {
			root = (Parent)loader.load();
			DrawController controller = (DrawController)loader.getController();
			Scene scene = new Scene(root, 1000, 1000);
			scene.getStylesheets().add("views/CanvasCSS.css");
			controller.start(user);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			System.out.println("Cant load!");
			e1.printStackTrace();
		}
	}

	/**
	 * Changes scene to log in menu, thus logs out
	 * @param e Acion of clicking
	 * @throws IOException can't load scene.
	 */
	@FXML
	public void logClick(ActionEvent e) throws IOException {
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Login.fxml"));
        root = (Parent)loader.load();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
	}
	
	/**
	 * Updates the message of the day.
	 */
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
