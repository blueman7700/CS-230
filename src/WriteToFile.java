import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This takes a map, and player name and saves the current state of the map saved as PlayerName.txt
 * @author Nathan Preston
 * @version 1.0
 */
public class WriteToFile {
	/**
	 * Takes in a map and a playername, then calls strToFile, passing levelToString to save the map
	 * @param m the current map
	 * @param name player name
	 */
	public void saveMap(Map m, String name) {
		strToFile(levelToString(m), name, false);
		//TODO: SAVE AI
	}
	
	/**
	 * Takes in a map, draws an ASCII version. Then calls detailsToString to get the map details
	 * @param m the current map
	 * @return String format of the current map
	 */
	public String levelToString(Map m) {
		String save = "";
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
						if(((Floor) m.getTile(x, y)).getContent() == null)  {
							
						} else {
							switch(((Floor) m.getTile(x, y)).getContent().getName()) {
								case("Key"):
									save = save +"KEY, "+((Key) ((Floor) m.getTile(x, y)).getContent()).getColour()
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
	 * Creates a new file, as the player's name
	 * @param data the map in String form
	 * @param name the player's name
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
