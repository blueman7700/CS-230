import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

/**
 * @author Nathan Hardy
 * @version 1.1
 */
public class LoginController {

	@FXML
	TextField textIn;
	@FXML
	Button loginBtn;
	@FXML
	Button deleteBtn;
	@FXML
	ListView<String> namesList;
	final static String FILE_PATH = "src/Files/Users.txt";
	static String CURR_NAME = "";
	static String FINAL_NAME ="";

	/**
	 * Constructor
	 */
	public LoginController() {};

	/**
	 * initialize the login controller
	 */
	@FXML
	public void initialize() {
		try {
			ArrayList<String> names = getUsers();
			ObservableList<String> items =FXCollections.observableArrayList (names);
			namesList.setItems(items);
			namesList.setPrefWidth(500);
			namesList.setPrefHeight(700);
		} catch (FileNotFoundException e2) {
			System.out.println("failed to display names");
		}
	}

	/**
	 * process login button click.
	 *
	 * @param e action event.
	 * @throws IOException
	 */
	@FXML
	public void loginClick(ActionEvent e) throws IOException {
		try {
			if(!textIn.getText().contains(" ")) {
				FINAL_NAME = login(textIn.getText());
			}else {
				System.out.println("nope");
			}
		} catch (Exception e1) {

		}
		if(FINAL_NAME.length() > 0) {
			//loads new stage by swapping root
			Parent root;
			Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Menu.fxml"));
			root = (Parent)loader.load();
			MenuController controller = (MenuController)loader.getController();
			Scene scene = new Scene(root, 1000, 1000);
			controller.start(FINAL_NAME);
			stage.setScene(scene);
			stage.show();
		}

	}

	/**
	 * process delete action.
	 *
	 * @param e action event.
	 */
	@FXML
	public void deleteClick(ActionEvent e) {
		try {
			deleteLine(textIn.getText());
			initialize();
		} catch (Exception e1) {

		}
	}


	/**
	 * get the users from the user file.
	 *
	 * @return users
	 * @throws FileNotFoundException
	 */
	private static ArrayList<String> getUsers() throws FileNotFoundException {
		ArrayList<String> names = new ArrayList<String>();
		File file = new File(FILE_PATH);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			CURR_NAME = in.next();
			names.add("User: " + CURR_NAME + " , Highest Level: " + in.nextInt());
		}
		in.close();
		return names;
	}

	/**
	 * start the game as a desired user or create a new user if it does not exist.
	 *
	 * @param name user name.
	 * @return user name and details
	 * @throws Exception
	 */
	private static String login(String name) throws Exception {
		ArrayList<String> names = getNames();
		String output = "";
		for(String i : names) {
			if(i.equals(name)) {
				output = name;
			}
		}
		if(output.equals("") && !name.equals("")) {
			output = name;
			String content = name+ " 0";
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true));
			writer.newLine();
			writer.write(content);
			writer.close();
		}

		return output;
	}

	/**
	 * Get all the users from the user file.
	 *
	 * @return all user names.
	 * @throws FileNotFoundException if file does not exist then exception is thrown.
	 */
	private static ArrayList<String> getNames() throws FileNotFoundException{
		ArrayList<String> names = new ArrayList<String>();
		File file = new File(FILE_PATH);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			CURR_NAME = in.next();
			names.add(CURR_NAME);

		}
		in.close();
		return names;
	}

	/**
	 * delete a user from the file.
	 *
	 * @param phrase user to delete
	 * @throws Exception
	 */
	private static void deleteLine(String phrase) throws Exception  {
		File file = new File(FILE_PATH);
		Scanner in = new Scanner(file);
		String content = "";
		while(in.hasNextLine()) {
			CURR_NAME = in.next();

			if(!(CURR_NAME.equals(phrase))) {
				content += CURR_NAME + " " + in.nextInt() +"\n";
			}else {
				in.nextInt();
			}
		}
		content = content.substring(0, content.length()-1);
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,false));
		writer.write(content);
		writer.close();
	}
}