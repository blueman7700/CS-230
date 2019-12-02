import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This takes a map, and player name and saves the current state of the map saved as PlayerName.txt
 * @author Nathan Preston
 * @version 1.0
 */
public class SaveMap {
	
	public SaveMap(Map m, String name) {
		strToFile(levelToString(m), name);
	}
	
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
						save = save + "T";
						break;
					case("KeyDoor"):
						save = save + "D";
						break;
					case("TokenDoor"):
						save = save + "T";
						break;
					default:
						save = save + "_";
						break;	
				}
			}
		}
		return save;
	}
	
	public void strToFile(String data, String name) {
        File file = new File("src/"+name+".txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
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
}
