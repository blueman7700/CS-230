import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This takes a map, and player name and saves the current state of the map saved as PlayerName.txt
 * @author Nathan Preston
 * @version 1.2
 */
public class WriteToFile {
	/**
	 * Takes in a map and a playername, then calls strToFile, passing levelToString to save the map
	 * @param m the current map
	 * @param name player name
	 */
	public void saveMap(Map m, String name, ArrayList<Entity> AI, String levelNum) {
		strToFile(addAI(levelToString(m), AI), name, false, levelNum);
	}

	/**
	 * Takes in a map, draws an ASCII version. Then calls detailsToString to get the map details
	 * @param m the current map
	 * @return String format of the current map
	 */
	public String levelToString(Map m) {
		String save = "";
		save = save + m.getHeight()+", "+m.getWidth()+"\n";
		for(int y = 0; y < m.getHeight(); y++) {
			for(int x = 0; x < m.getWidth(); x++) {
				switch(m.getTile(x, y).getClass().getName()) {
					case("Wall"):
						save = save + "#";
						break;
					case("Water"):
						save = save + "w";
						break;
					case("Fire"):
						save = save + "f";
						break;
					case("Goal"):
						save = save + "g";
						break;
					case("Teleporter"):
						save = save + "t";
						break;
					case("KeyDoor"):
						save = save + "d";
						break;
					case("TokenDoor"):
						save = save + "d";
						break;
					default:
						save = save + "_";
						break;
				}
			}
			save = save + "\n";
		}

		save = detailsToString(m, save);

		return save;
	}

	/**
	 * Takes in the current map and finds any items/doors/teleporters and adds the specific details
	 * @param m current map
	 * @param inSave the String passed from levelToString, to update
	 * @return the updated string from levelToString, including map details
	 */
	public String detailsToString(Map m, String inSave) {
		String save = inSave;//save value
		Boolean teleporter = false;//there's two teleporters in the map, only need one to be read
		int startx = m.getStartX() + 1;
		int starty = m.getStartY() + 1;


		save = save + "START, "+startx+", "+starty+"\n";

		for(int y = 0; y < m.getHeight(); y++) {
			for(int x = 0; x < m.getWidth(); x++) {

				switch(m.getTile(x, y).getClass().getName()) {
					case("Floor"):
						if(((Floor) m.getTile(x, y)).contains())  {
							switch(((Floor) m.getTile(x, y)).getContents().getClass().getName()) {
							case("Key"):
								save = save +"KEY, "+((Key) ((Floor) m.getTile(x, y)).getContents()).getColour()
											+", "+(x+1)+", "+(y+1)+"\n";
								break;
							case("Token"):
								save = save + "TOKEN, "+(x+1)+", "+(y+1)+"\n";
								break;
							case("Flippers"):
								save = save + "ITEM, FLIP, "+(x+1)+", "+(y+1)+"\n";
								break;
							case("FireBoots"):
								save = save + "ITEM, BOOT, "+(x+1)+", "+(y+1)+"\n";
								break;
							}
						}
						break;

					case("KeyDoor"):
						save = save + "DOOR, KEY, "+((KeyDoor) m.getTile(x, y)).getColour()+", "+(x+1)+", "+(y+1)+"\n";
						break;
					case("TokenDoor"):
						save = save + "DOOR, TOKEN, "+((TokenDoor) m.getTile(x, y)).getNum()+", "+(x+1)+", "+(y+1)+"\n";
						break;
					case("Teleporter"):
						if(teleporter == false) {
							Teleporter t = ((Teleporter) m.getTile(x, y)).getPartner();
							save = save + "TELE, "+(x+1)+", "+(y+1)+", "+(t.getxPos()+1)+", "+(t.getyPos()+1)+"\n";
							teleporter = true;
						}
						break;
				}
			}
		}


		return save;
	}

	/**
	 * Adds AI to the save file
	 * @param s already made save file
	 * @param enemies list of enemies
	 * @return the updated save file
	 */
	public String addAI(String s, ArrayList<Entity> enemies) {
		String save = s;
		int x;
		int y;
		for(Entity en : enemies) {
			x = en.getxPos() + 1;
			y = en.getyPos() + 1;
			if(en.getClass().getName() == "DumbAI") {
				save = save + "AI, DUMB, "+x+", "+y+"\n";;
			} else if (en.getClass().getName() == "SmartAI") {
				save = save + "AI, SMART, "+x+", "+y+"\n";
			} else if (en.getClass().getName() == "WallAI") {
				save = save + "AI, WALL, "+x+", "+y+"\n";
			} else {
				s = s + "AI, LINE, "+((LineAI) en).getDirection()+", "+x+", "+y+"\n";;
			}
		}
		return s;
	}

	/**
	 * Creates a new file, as the player's name
	 * @param data the map in String form
	 * @param name the player's name
	 */
	public void strToFile(String data, String name, Boolean b, String levelNum) {
        File file = new File("src/Files/"+name+levelNum+".txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, b);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * Saves the top 10 players: their username and score
	 * @param input string value of the leaderboard
	 */
	public void saveLeaderboard(String input) {
		String leaderboard = input + "\n";

		strToFile(leaderboard, "Leaderboard", false, "");
	}

	/**
	 * Adds a new name to the list of players
	 * @param input the String value of the new name
	 */
	public void savePlayer(String input) {
		String name = input + "\n";;

		strToFile(name, "Users", true, "");
	}
}
