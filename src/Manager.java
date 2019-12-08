import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Handles interactions between most classes. User inputs are listened for here. The main game is drawn and ran here.
 * @author Lewis Pettifer
 *
 */
public class Manager {

	// The size of each cell
	private static int GRID_CELL_SIZE = 50;
	// Load images
	private Image tile;
	private Image playerImg;
	private Image dumbAIImg;
	private Image smartAIImg;
	private Image wallAIImg;
	private Image lineAIImg;
	//the player
	private Player player;
	//all the enemies
	private ArrayList<Entity> enemies;
	//the map
	private Map map;
	//File reader
	private FileReader fr;
	//path to current level
	private String levelPath;
	private String level;
	//The current user logged in
	private String user;
	//first start of timer
	Instant first;
	
	//GUI elements
	@FXML
	private Canvas gameCanvas;
	@FXML
	private Button exitBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private Text tokenLbl;
	@FXML
	private Text blueLbl;
	@FXML
	private Text redLbl;
	@FXML
	private ImageView flipImg;
	@FXML
	private ImageView bootImg;
	
	/**
	 * Constructor
	 * @param level The level to be played
	 * @param user The user playing
	 */
	public Manager(String level, String user) {
		this.level = level;
		this.levelPath = "src/Files/"+level+".txt";
		this.user = user;
		//start the timer
		first = Instant.now();
	}
	
	/**
	 * Sets listener for movements when user presses a key
	 * @param scene The scene to have the listener
	 */
	public void start(Scene scene) {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
	}
		
	/**
	 * initalizes the controller
	 */
	@FXML
	public void initialize() {
		
		//load filereader
		fr = new FileReader(levelPath);
		//loads the map and sets the tiles
		map = new Map(fr.getHeight(), fr.getWidth(), fr.fileToArray(), fr.getStartX(), fr.getStartY(), fr.getEnemies(), this);
		//load the player
		player = new Player(fr.getStartX(), fr.getStartY(), this);
		//add any saved items/ tokens to the player
		for(Item e : fr.readItems()) {
			player.addItemToInv(e);
		}
		player.setTokens(fr.readTokens());
		//load ai
		enemies = addAI();
		// Load images
		playerImg = new Image("sprites/player.png");
		dumbAIImg = new Image("sprites/dumbAI.png");
		smartAIImg = new Image("sprites/smartAI.png");
		lineAIImg = new Image("sprites/lineAI.png");
		wallAIImg = new Image("sprites/wallAI.png");
		
		gameCanvas.setWidth(map.getWidth() * GRID_CELL_SIZE);
		gameCanvas.setHeight(map.getHeight() * GRID_CELL_SIZE);

		//set screen display to default values
		tokenLbl.setText("0");
		blueLbl.setText("0");
		redLbl.setText("0");
		bootImg.setOpacity(0);
		flipImg.setOpacity(0);
		
		drawGame();
	}
	

	
	/**
	 * Draw the game on the canvas.
	 */
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = gameCanvas.getGraphicsContext2D();

		
		// Clear canvas
		gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

		// We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
		// We draw the row at y value 2.		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				
				//draws each key door colour or draws the other types of tiles
				if(map.getTile(x, y) instanceof KeyDoor) {
					tile = new Image("sprites/Door"+((KeyDoor)map.getTile(x, y)).getColour()+".png");
					gc.drawImage(tile, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE);
				}else {
					tile = new Image(map.getTile(x, y).getFilePath());
					gc.drawImage(tile, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE);
					if (( (map.getTile(x, y))).contains() == true) {
						//checks to see what the contents is then add is
	                	if (((Floor) (map.getTile(x, y))).getKey() != null) {
	                		tile = new Image("sprites/Key"+ ((Floor) map.getTile(x, y)).getKey().getColour() +".png");
	                	}
	                	if (((Floor) (map.getTile(x, y))).getToken() != null) {
	                		tile = new Image("sprites/Token.png");
	                	}
	                	if (((Floor) (map.getTile(x, y))).getFlippers() != null) {
	                		tile = new Image("sprites/Flippers.png");
	                	}
	                	if (((Floor) (map.getTile(x, y))).getFireBoots() != null) {
	                		tile = new Image("sprites/Boots.png");
	                	}
						gc.drawImage(tile, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE);
					}
				}					
			}
		}

		//display the number of tokens on screen
		tokenLbl.setText(Integer.toString(player.getTokens()));
		int totRKey = 0;
		int totBKey = 0;

		for (Item i : player.getInv()) {

			if (i instanceof Key) {
				if (((Key) i).getColour().equals("RED")) {
					totRKey++;
				}else {
					totBKey++;
				}
			}else if (i instanceof FireBoots) {
				bootImg.setOpacity(1);
			}else if (i instanceof Flippers) {
				flipImg.setOpacity(1);
			}
		}
		redLbl.setText(Integer.toString(totRKey));
		blueLbl.setText(Integer.toString(totBKey));

		// Draw player at current location
		gc.drawImage(playerImg, player.getxPos() * GRID_CELL_SIZE, player.getyPos() * GRID_CELL_SIZE);

		//Draw all enemies
		for (Entity e : enemies) {
			if (e instanceof DumbAI) {
				gc.drawImage(dumbAIImg, e.getxPos() * GRID_CELL_SIZE, e.getyPos() * GRID_CELL_SIZE);
			}else if (e instanceof SmartAI) {
				gc.drawImage(smartAIImg, e.getxPos() * GRID_CELL_SIZE, e.getyPos() * GRID_CELL_SIZE);
			}else if (e instanceof WallAI) {
				gc.drawImage(wallAIImg, e.getxPos() * GRID_CELL_SIZE, e.getyPos() * GRID_CELL_SIZE);
			}else {
				gc.drawImage(lineAIImg, e.getxPos() * GRID_CELL_SIZE, e.getyPos() * GRID_CELL_SIZE);
			}
		}
	}
	
	/**
	 * Called when a key is pressed, moves player according to WASD key pressed.
	 * Use WASD to move.
	 * @param event the action of a key press
	 */
	public void processKeyEvent(KeyEvent event) {
		switch (event.getCode()) {
			
			case RIGHT:
		    	for(Entity e : enemies) {
	    			e.move(MoveType.AUTO);
	    		}
		    	// Right key was pressed. So move the player right by one cell.
	        	player.move(MoveType.RIGHT);
	        	//sees if a player is on an item then removes it if so
	        	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	        		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
	        	
	        	break;
		    case LEFT:
		    	for(Entity e : enemies) {
					e.move(MoveType.AUTO);
				}
		    	//Left key was pressed. So move the player left by one cell.
		    	player.move(MoveType.LEFT);
		    	//sees if a player is on an item then removes it if so
		    	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	        		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
		    	
		    	break;
		    case UP:
		    	for(Entity e : enemies) {
					e.move(MoveType.AUTO);
				}
		    	//Up key was pressed so move the player up by one cell.
		    	player.move(MoveType.DOWN);
		    	//sees if a player is on an item then removes it if so
		    	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	        		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
		    	
		    	break;
		    case DOWN:
		    	for(Entity e : enemies) {
					e.move(MoveType.AUTO);
				}
		    	//Down key was pressed so move the player down by one cell.
		    	player.move(MoveType.UP);
		    	//sees if a player is on an item then removes it if so
		    	if (map.getTile(player.getxPos(), player.getyPos()) instanceof Floor) {
	       		((Floor) map.getTile(player.getxPos(), player.getyPos())).removeContents();
	        	}
		    	
		    	break;
	        default:
	        	// Do nothing
	        	break;
		}
		
		if(collision()) {
			lose();
		}
		
		// Redraw game as the player may have moved.
		drawGame();
		
		// Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
		event.consume();
	}

	/**
	 * Loads the AI from the map
	 * @return the AI's
	 */
	public ArrayList<Entity> addAI() {
		int[] coods = new int[2];
		ArrayList<Entity> ai = new ArrayList<>();

		for(String s : map.getEnemies()) {
			if(s.contains("DUMB")) {
				s = s.substring(6);
				coods = coodsFromString(s);
				ai.add(new DumbAI(coods[0],coods[1],this));
			} else if (s.contains("SMART")){
				s = s.substring(7);
				coods = coodsFromString(s);
				ai.add(new SmartAI(coods[0],coods[1],this));
			} else if (s.contains("WALL")){
				s=s.substring(6);
				coods = coodsFromString(s);
				ai.add(new WallAI(coods[0], coods[1], this));
			}else if (s.contains("LINE")){
				s = s.substring(6);
				if(s.contains("UP")) {
					s = s.substring(4);
					coods = coodsFromString(s);
					ai.add(new LineAI(coods[0],coods[1],this, MoveType.UP));
				} else if(s.contains("DOWN")) {
					s = s.substring(6);
					coods = coodsFromString(s);
					ai.add(new LineAI(coods[0],coods[1],this, MoveType.DOWN));
				} else if(s.contains("LEFT")) {
					s = s.substring(6);
					coods = coodsFromString(s);
					ai.add(new LineAI(coods[0],coods[1],this, MoveType.LEFT));
				} else {
					s = s.substring(7);
					coods = coodsFromString(s);
					ai.add(new LineAI(coods[0],coods[1],this, MoveType.RIGHT));
				}
				
			}
		}
		return ai;
	}
	
	/**
	 * Checks if player is at the some position as any enemy
	 * @return where the player is colliding
	 */
	public Boolean collision() {
		for(Entity i : enemies) {
			
			int px = player.getxPos();
			int py = player.getyPos();
			int ax = i.getxPos();
			int ay = i.getyPos();
			if(px == ax && py == ay) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Called when player wins the level
	 */
	public void win(){
		//stops timer
		Instant second = Instant.now();
		Duration duration = Duration.between(first, second);
		
		//sees if it is a user save and if so added the current level to the filepath
		if(level.equals(user)) {
			int levelNum = fr.readLevel();
			System.out.println(levelNum);
			level = level+levelNum;
			levelPath = "src/Files/"+level+".txt";
		}
		
		FileReader ufr = new FileReader("src/Files/Users.txt");
		String userInfo = ufr.readUser(user);
		
		//sees if the user as reached a new high level
		int userCurrent = Integer.parseInt(level.substring(level.length()-1));
		int userMax = Integer.parseInt(userInfo.substring(userInfo.length()-1));
		if(userCurrent > userMax) {
			updateUser(user, userCurrent);
		}
		
		//change scene to win		
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)gameCanvas.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Win.fxml"));
        try {
			root = (Parent)loader.load();
			WinController controller = (WinController)loader.getController();
	        Scene scene = new Scene(root, 1000, 1000);
	        controller.start(levelPath, user, Long.toString(duration.getSeconds()));
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error loading scene");
		}
	}
	
	/**
	 * Updates the highest level reached for the given user
	 * @param user the user to be updated
	 * @param userCurrent the new highest level
	 */
	private void updateUser(String user, int userCurrent) {
		
		//opens file
		File file = new File("src/Files/Users.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
		
		String data = "";
		String currentLine;
		
		//loops over each line to build the new data
		while(in.hasNextLine() && in.hasNext()) {
			currentLine = in.nextLine();
			if(currentLine.contains(user)) {
				data = data + user + " " + userCurrent + "\n";
			} else {
				data = data + currentLine + "\n";
			}
		}
		
		//remove extra end lines
		data = data.trim();
		
		//writes to file
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cant write file!");
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Cant close file!");
			}
		}
		
	}

	public void lose() {
		//stops timer
		Instant second = Instant.now();
		Duration duration = Duration.between(first, second);
		
		//sees if it is a user save and if so added the current level to the filepath
		if(level.equals(user)) {
			int levelNum = fr.readLevel();
			level = level+levelNum;
			levelPath = "src/Files/"+level+".txt";
		}
		
		//change scene to lose
		//loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)gameCanvas.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Lose.fxml"));
        try {
			root = (Parent)loader.load();
			LoseController controller = (LoseController)loader.getController();
	        Scene scene = new Scene(root, 1000, 1000);
	        controller.start(levelPath, user, Long.toString(duration.getSeconds()));
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error loading scene");
		}
	}
	
	/**
	 * Takes in coodinates and returns the X and Y separately 
	 * @param input the coods
	 * @return the int format of the coods
	 */
	public int[] coodsFromString(String input) {
		int[] coods = new int[2];
		if(input.charAt(1) == ',') {
			coods[0] = Integer.valueOf(input.substring(0, 1)) - 1;//if next char is a comma then its 1 digit (X)
			input = input.substring(3);
		} else {
			coods[0] = Integer.valueOf(input.substring(0, 2)) - 1;//otherwise it's 2 digits
			input = input.substring(4);
		}
	
		if(input.length() > 1) {
			coods[1] = Integer.valueOf(input.substring(0, 2)) - 1;//if there's more than one digit then its a 2 digit num (Y)
		} else {
			coods[1] = Integer.valueOf(input.substring(0, 1)) - 1;//otherwise just 1 digit
		}
		
		return coods;
	}
	
	/**
	 * get the game map.
	 * @return game map.
	 */

	public Map getMap() {
		return map;
	}

	/**
	 * get the player.
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	@FXML
	public void exitClick(ActionEvent e) throws IOException {
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
	
	@FXML
	public void saveClick(ActionEvent e) {
		
		if(level.equals(user)) {
			int levelNum = fr.readLevel();
			level = level+levelNum;
			levelPath = "src/Files/"+level+".txt";
		}
		
		String levelNum = levelPath.substring(levelPath.length()-5, levelPath.length()-4);
		new WriteToFile().saveMap(map, user, enemies, levelNum, player);
	}
	
}
