import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
import javafx.stage.Stage;

/**
 *Controller for the leaderboard GUI
 */
public class leaderboardController {
	@FXML
	Button backBtn;
	@FXML
	ListView<String> scores;

	/**
	 * create a new instance of the leaderboard controller.
	 */
	public leaderboardController() {};

	/**
	 * initialize the leaderboard.
	 */
	@FXML
	public void initialize() {
		ArrayList<String> fullBoard = new ArrayList<String>();
		int levelNum = 1;
		boolean fileExists = true;
		try {
			while(fileExists) {
				try {
					fullBoard = getTop(levelNum,fullBoard);
					levelNum++;
				}catch(Exception e2) {
					fileExists = false;
				}
			}
		} catch (Exception e) {

		}
		ObservableList<String> items =FXCollections.observableArrayList (fullBoard);
		scores.setItems(items);
		scores.setPrefWidth(500);
		scores.setPrefHeight(700);
	}

	/**
	 * leave the leaderboard and return to the menu.
	 *
	 * @param e
	 * @throws IOException
	 */
	@FXML
	public void backClick(ActionEvent e) throws IOException {
		//loads new stage by swapping root
		Parent root;
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Menu.fxml"));
		root = (Parent)loader.load();
		MenuController controller = (MenuController)loader.getController();
		Scene scene = new Scene(root, 1000, 1000);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * get the top players for a given level.
	 *
	 * @param levelNum 			level to get player stats from.
	 * @param fullLeaderboard	leaderboard to populate.
	 * @return					populated leaderboard.
	 * @throws Exception
	 */
	private ArrayList<String> getTop(int levelNum,ArrayList<String> fullLeaderboard) throws Exception{
		int displayNum = 3;
		System.out.println("in method");
		String fileName = "src/Files/Leaderboard" + levelNum + ".txt";
		File ranks = new File(fileName);
		Scanner in = new Scanner(ranks);
		System.out.println("opnedScanner");
		int fileLines = 0;
		System.out.println(fileLines);
		while(in.hasNextLine()) {
			fileLines++;
			in.nextLine();
		}
		if(fileLines<displayNum) {
			displayNum = fileLines;
		}
		in.close();
		System.out.println("scanner closed");
		HashMap<String, Integer> positions = new HashMap<>();
        LinkedHashMap<String, Integer> sortedPosition = new LinkedHashMap<>();
        
        
        FileReader ab = null;
        ab = new FileReader(ranks);

		System.out.print(displayNum);
        BufferedReader br = new BufferedReader(ab);
        String line;
        while ((line = br.readLine()) != null) {
        	String[] splt = line.split(" ");
        	positions.put(splt[0],Integer.parseInt(splt[1]));
        }
        positions.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(x -> sortedPosition.put(x.getKey(), x.getValue()));
        Set<Map.Entry<String, Integer>> mapSet = sortedPosition.entrySet();
        fullLeaderboard.add("Level "+levelNum+ " Top Scores:");
        for(int i = 1;i<displayNum+1;i++) {
        	Map.Entry<String, Integer> partAt = (new ArrayList<Map.Entry<String, Integer>>(mapSet)).get(positions.size()-i);
        	System.out.println(partAt.toString().replace("=","   "));
        	fullLeaderboard.add( partAt.toString().replace("=","   "));
        }
        //br.close();
        fullLeaderboard.add("");
        return fullLeaderboard;
	}
	
}
