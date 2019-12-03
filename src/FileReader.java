import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class will take a text file and create a 2D array
 * @author Nathan Preston
 * @version 1.0
 */
public class FileReader {
	
	private static String filename;//filepath/filename 
	private int startX;//player's starting X cood
	private int startY;//player's starting Y cood
	
	public FileReader(String filename) {
		this.filename = filename;
	}
	
	public Tile[][] fileToArray(){
		Tile[][] level = new Tile[getHeight()][getWidth()];
		
		int count = 0;
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
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
	 * takes the filename, checks if it is real. If so creates a scanner from it
	 * @param filename, filename of .txt file
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
	 * Takes filename as an input, and returns the map height - found at the top of the file
	 * @param filename is the directory 
	 * @return the height of the map
	 */
	public int getHeight() {
		Scanner in = readDataFile();
		
		in.useDelimiter("(\\p{javaWhitespace}|,)+");//Change Delimeter to also ','
		
		return in.nextInt();
	}
	
	/**
	 * Takes filename as an input, and returns the map width - found at the top of the file
	 * @param filename is the directory 
	 * @return the width of the map
	 */
	public int getWidth() {
		Scanner in = readDataFile();
		
		in.useDelimiter("(\\p{javaWhitespace}|,)+");//Change Delimeter to also ','
		in.nextInt();//removes the height from the input
		
		return in.nextInt();
	}
	
	/**
	 * This method takes in a filename, reads and returns the ASCII map into String format
	 * @param filename is the directory 
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
	 * This method takes in a filename, reads and returns the information found below the ASCII map in string form
	 * @param filename is the directory 
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
				type = "d";
				input = input.substring(6);
			} else if (input.contains("LINE")) {
				type = "l";
				input = input.substring(6);
			} else if (input.contains("WALL")) {//checking for the type of AI
				type = "w";
				input = input.substring(6);
			} else {
				type = "s";
				input = input.substring(7);//constantly removing unneeded parts of the input
			}
			
			if(input.contains("NORTH")) {//get the way the AI its facing
				extra = "north";
				input = input.substring(7);
			} else if(input.contains("SOUTH")) {
				extra = "south";
				input = input.substring(7);
			} else if(input.contains("EAST")) {
				extra = "east";
				input = input.substring(6);
			} else {
				extra = "west";
				input = input.substring(6);
			}
			
			//TODO: ADD AI MOVEMENT
			 
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
			
			/* CREATE AND PUT IN DOOR TO MAP */
			 			
		} else if(input.contains("ITEM")) {//type of misc item
			input = input.substring(6);
			if(input.contains("FLIP")) {
				input = input.substring(6);
				((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new Flippers("Flip"));
			} else {
				input = input.substring(6);
				((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new FireBoots("Boot"));
			}
			 
		} else if(input.substring(0, 3).contains("KEY")) { //have to use a substring as some doors have 'KEY' in their line
			input = input.substring(5);
			
			while(input.charAt(count) != ',') {
				extra = extra + input.charAt(count);//gets the colour
				count++;
			}
			input = input.substring(count+2);
			((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new Key("Key", extra));
			 
		} else if (input.substring(0, 5).contains("TOKEN")) {
			input = input.substring(7);
			((Floor) level[coodsFromString(input)[1]][coodsFromString(input)[0]]).setContent(new Token("Token"));
			 
		} else if (input.contains("TELE")) {
			input = input.substring(6);
			int[] xy1 = coodsFromString(input.substring(0, 4));
			input = input.substring(6);
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
	 * Sets the current filename, allows reuse of object
	 * @param filename new filename/file path
	 */
	public void setFileName(String filename) {
		this.filename = filename;
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