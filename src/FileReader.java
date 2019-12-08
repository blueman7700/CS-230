import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class will take a text file and create a 2D array
 * @author Nathan Preston
 * @version 1.1
 */
public class FileReader {

	private static String filename;//filepath/filename
	private int startX;//player's starting X cood
	private int startY;//player's starting Y cood
	private ArrayList<String> enemies = new ArrayList<String>();

	public FileReader(String filename) {
		this.filename = filename;
	}

	public Tile[][] fileToArray(){
		int h = getHeight();
		int w = getWidth();
		Tile[][] level = new Tile[h][w];

		int count = 0;
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				level[y][x] = charToObject(readMap().charAt(count));//takes a character from the string and inputs a Tile
				count++;
			}
		}
		for(String s : readDetails()) {
			level = addDetails(s, level);
		}
		return level;
	}

	/**
	 * takes the filename, checks if it is real. If so creates a scanner from it.
	 *
	 * @return the scanner of the file
	 */
	public static Scanner readDataFile() {
		File newFile = new File(filename);
		Scanner in = null;

		try {
			in = new Scanner(newFile);

		} catch (FileNotFoundException e) {
			System.out.println("Not Found");	//if file not found, throw exception
			System.exit(0);
		}

		return in;
	}

	/**
	 * File to string for Users and Leaderboard
	 * @return string value of the read file
	 */
	public String readFile() {
		String leaderboard = "";
		Scanner in = readDataFile();

		while(in.hasNextLine() && in.hasNext()) {
			leaderboard = leaderboard + in.nextLine() + "\n";
		}

		return leaderboard;
	}
	
	public String readUser(String userCheck) {
		
		String user = "";
		Scanner in = readDataFile();
		
		while(in.hasNextLine() && in.hasNext()) {
			user = in.nextLine();
			if(user.contains(userCheck)) {
				return user;
			}
		}
		return "search failed!";
		
	}
	
	/**
	 * Find the current level of the file
	 * @return the file level
	 */
	public int readLevel() {
		Scanner in = readDataFile();
		System.out.println("reading level");
		String line = in.nextLine();
		while(in.hasNext()) {//loops through file to the level
			line = in.nextLine();
			System.out.println(line);
		}
		System.out.println("Line:" + line);
		System.out.println(line.substring(line.length()-1));
		return Integer.parseInt(line.substring(line.length()-1));
	}

	/**
	 * Takes filename as an input, and returns the map height - found at the top of the file.
	 *
	 * @return the height of the map
	 */
	public int getHeight() {
		Scanner in = readDataFile();

		in.useDelimiter("(\\p{javaWhitespace}|,)+");//Change Delimeter to also ','

		return in.nextInt();
	}

	/**
	 * Takes filename as an input, and returns the map width - found at the top of the file.
	 *
	 * @return the width of the map
	 */
	public int getWidth() {
		Scanner in = readDataFile();

		in.useDelimiter("(\\p{javaWhitespace}|,)+");//Change Delimeter to also ','
		in.next();//removes the height from the input

		return in.nextInt();
	}

	/**
	 * This method takes in a filename, reads and returns the ASCII map into String format.
	 *
	 * @return the ASCII map as a string
	 */
	public String readMap() {
		String output = new String();//output string

		Scanner in = readDataFile();

		while(in.hasNextLine() && in.hasNext()) {//loops through the map layout
			String line = in.nextLine();
			if(line.contains("#")) {
				output = output + line;
			}
		}

		return output;
	}

	/**
	 * This method takes in a filename, reads and returns the information found below the ASCII map in string form.
	 *
	 * @return the extra map details in an arraylist
	 */
	public ArrayList<String> readDetails() {
		ArrayList<String> output = new ArrayList<String>();

		Scanner in = readDataFile();
		in.useDelimiter("(\\p{javaWhitespace}|,)+");//Change Delimeter to also ','

		in.nextLine(); //removes the map size from the input

		while(in.hasNextLine() && in.hasNext()) {//loops through to the end of the map layout
			String line = in.nextLine();
			if(!line.contains("#")) {
				output.add(line);
			}
		}

		return output;
	}

	/**
	 * Takes a character and returns the Tile equivalent
	 * @param input is the character
	 * @return the Tile equivalent to the character
	 */
	public Tile charToObject(char input) {
		switch(input) {
			case '#':
				return new Wall();
			case 'f':
				return new Fire();
			case 'w':
				return new Water();
			case 'g':
				return new Goal();
			default:
				return new Floor();
		}
	}

	/**
	 * adds the details specified under the ASCII drawing of the map
	 * @param input one detail (one line from the file)
	 */
	public Tile[][] addDetails(String input, Tile[][] level) {
		String type = "";//the type of AI
		String extra = "";//extra info. e.g. key colour
		int count = 0;//count of tokens for a door

		if(input.contains("START")) {//if its START
			startX = coodsFromString(input.substring(7))[0];//remove 'START, ' from the string leaving just the coods
			startY = coodsFromString(input.substring(7))[1];
		} else if (input.contains("AI")) {//if AI
			input = input.substring(4);
			if(input.contains("DUMB")) {
				type = "DUMB";
				input = input.substring(6);
				addEnemy(type+", "+String.valueOf(coodsFromString(input)[0])+", "+String.valueOf(coodsFromString(input)[1]));
			} else if (input.contains("LINE")) {
				type = "LINE";
				input = input.substring(6);

				if(input.contains("NORTH")) {//get the way the AI its facing
					extra = "UP";
					input = input.substring(7);
				} else if(input.contains("SOUTH")) {
					extra = "DOWN";
					input = input.substring(7);
				} else if(input.contains("EAST")) {
					extra = "LEFT";
					input = input.substring(6);
				} else {
					extra = "RIGHT";
					input = input.substring(6);
				}

				addEnemy(type+", "+extra+", "+String.valueOf(coodsFromString(input)[0])+", "+String.valueOf(coodsFromString(input)[1]));
			} else if (input.contains("WALL")) {//checking for the type of AI
				type = "WALL";
				input = input.substring(6);
				addEnemy(type+", "+String.valueOf(coodsFromString(input)[0])+", "+String.valueOf(coodsFromString(input)[1]));
			} else {
				type = "SMART";
				input = input.substring(7);//constantly removing unneeded parts of the input
				addEnemy(type+", "+String.valueOf(coodsFromString(input)[0])+", "+String.valueOf(coodsFromString(input)[1]));
			}
		} else if(input.contains("DOOR")) { //if door
			input = input.substring(6);
			if(input.contains("KEY")) {
				input = input.substring(5);

				while(input.charAt(count) != ',') {
					extra = extra + input.charAt(count);//gets the colour
					count++;
				}

				input = input.substring(count+2);
				level[coodsFromString(input)[1]][coodsFromString(input)[0]] = (new KeyDoor(extra));
			} else {
				input = input.substring(7);
				count = Integer.valueOf(input.substring(0,1));//the number of tokens the door needs
				input = input.substring(3);
				level[coodsFromString(input)[1]][coodsFromString(input)[0]] = (new TokenDoor(count));
			}

		} else if(input.contains("ITEM")) {//type of misc item
			input = input.substring(6);
			if(input.contains("FLIP")) {
				input = input.substring(6);
				((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new Flippers());
			} else {
				input = input.substring(6);
				((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new FireBoots());
			}

		} else if(input.substring(0, 3).contains("KEY")) { //have to use a substring as some doors have 'KEY' in their line
			input = input.substring(5);

			while(input.charAt(count) != ',') {
				extra = extra + input.charAt(count);//gets the colour
				count++;
			}
			input = input.substring(count+2);
			((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new Key(extra));

		} else if (input.substring(0, 5).contains("TOKEN")) {
			System.out.print(input.substring(7));
			input = input.substring(7);
			((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new Token());

		} else if (input.contains("TELE")) {
			input = input.substring(6);
			int[] xy1;

			if(input.substring(4, 5).equals(",")) {
				xy1 = coodsFromString(input.substring(0, 4));
				input = input.substring(6);
			} else if(input.substring(5, 6).equals(",")) {
				xy1 = coodsFromString(input.substring(0, 5));
				input = input.substring(7);
			} else {
				xy1 = coodsFromString(input.substring(0, 6));
				input = input.substring(8);
			}
			int[] xy2 = coodsFromString(input);

			Teleporter t1 = new Teleporter(xy1[0], xy1[1]);
			Teleporter t2 = new Teleporter(xy2[0], xy2[1]);
			t1.setPartner(t2);
			t2.setPartner(t1);
			level[xy1[1]][xy1[0]] = t1;
			level[xy2[1]][xy2[0]] = t2;
		}

		return level;
	}

	/**
	 * Takes in the file's coodinates and returns the X and Y separately
	 * @param input the String format of the coods
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
	 * Add new enemy found in file to enemy ArrayList
	 * @param String of enemy details
	 */
	public void addEnemy(String enemy) {
		enemies.add(enemy);
	}

	/**
	 * Gets the ArrayList of enemies
	 * @return list of String details about the enemies
	 */
	public ArrayList<String> getEnemies(){
		return enemies;
	}


	/**
	 * gets the current filename
	 * @return String form of the filename
	 */
	public String getFileName() {
		return filename;
	}

	/**
	 * gets the X coodinate of where the player starts
	 * @return int value of X coodinate
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * gets the Y coodinate of where the player starts
	 * @return int value of Y coodinate
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Sets the current filename, allows reuse of object
	 * @param filename new filename/file path
	 */
	public void setFileName(String filename) {
		this.filename = filename;
	}

	/**
	 * sets the player's starting X coodinate
	 * @param x int value of the X coodinate
	 */
	public void setStartX(int x) {
		startX = x;
	}

	/**
	 * sets the player's starting Y coodinate
	 * @param y int value of the Y coodinate
	 */
	public void setStartY(int y) {
		startY = y;
	}

}
