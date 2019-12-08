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
	 * Saves the map, player, and AI to a file.
	 * @param m The map
	 * @param name the Username
	 * @param AI All the AI of that map
	 * @param levelNum The level number
	 * @param p The player
	 */
	public void saveMap(Map m, String name, ArrayList<Entity> AI, String levelNum, Player p) {
		strToFile(addLevel(addAI(levelToString(m, p), AI), levelNum), name, false);
	}

	/**
	 * Takes in a map, draws an ASCII version. Then calls detailsToString to get the map details
	 * @param m the current map
	 * @param p the player
	 * @return String format of the current map
	 */
	public String levelToString(Map m, Player p) {
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

		save = detailsToString(m, save, p);

		return save;
	}

	/**
	 * Takes in the current map and finds any items/doors/teleporters and adds the specific details
	 * @param m current map
	 * @param inSave the String passed from levelToString, to update
	 * @param p the player
	 * @return the updated string from levelToString, including map details
	 */
	public String detailsToString(Map m, String inSave, Player p) {
		String save = inSave;//save value

		save = save + "START, "+(p.getxPos()+1)+", "+(p.getyPos()+1)+"\n";

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
							Teleporter t = ((Teleporter) m.getTile(x, y)).getPartner();
							save = save + "TELE, "+(x+1)+", "+(y+1)+", "+(t.getxPos()+1)+", "+(t.getyPos()+1)+"\n";
						break;
				}
			}
		}

		//Saves the players inventory and tokens
		save = save + "INV, ";
		
		for(Item i : p.getInv()) {
			if(i instanceof Flippers) {
				save = save + "FLIP, ";
			}else if(i instanceof FireBoots) {
				save = save + "BOOTS, ";
			}else if(i instanceof Key) {
				save = save + "KEY, " + ((Key)i).getColour() + ", ";
			}else {
				System.out.println("unknown item!");
			}
		}
		
		save = save + p.getTokens() + "\n";
		
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
			x = en.getxPos();
			y = en.getyPos();
			System.out.println("x: "+x+"y: "+y);
			if(en instanceof DumbAI) {
				save = save + "AI, DUMB, "+(x+2)+", "+(y+2)+"\n";;
			} else if (en instanceof SmartAI) {
				save = save + "AI, SMART, "+(x+2)+", "+(y+2)+"\n";
			} else if (en instanceof WallAI) {
				save = save + "AI, WALL, "+(x+2)+", "+(y+2)+"\n";
			} else if (en instanceof LineAI){
				save = save + "AI, LINE, "+((LineAI) en).getDirection()+", "+(x+2)+", "+(y+2)+"\n";;
			} else {
				
			}
		}
		return save;
	}

	/**
	 * Adds the current level to the save file
	 * @param s already made save file
	 * @param level the current level
	 * @return the updated save file
	 */
	public String addLevel(String s, String level) {
		return (s = s + "LEVEL, "+level);
	}

	/**
	 * Creates a new file, as the player's name
	 * @param data the map in String form
	 * @param name the player's name
	 * @param b true.
	 */
	public void strToFile(String data, String name, Boolean b) {
        File file = new File("src/Files/"+name+".txt");
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

		strToFile(leaderboard, "Leaderboard", false);
	}

	/**
	 * Adds a new name to the list of players
	 * @param input the String value of the new name
	 */
	public void savePlayer(String input) {
		String name = input + "\n";;

		strToFile(name, "Users", true);
	}
}
